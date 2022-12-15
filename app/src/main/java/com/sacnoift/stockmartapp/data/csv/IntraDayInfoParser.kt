package com.sacnoift.stockmartapp.data.csv

import com.opencsv.CSVReader
import com.sacnoift.stockmartapp.data.mapper.toIntraDayInfo
import com.sacnoift.stockmartapp.data.remote.dto.IntraDayInfoDto
import com.sacnoift.stockmartapp.domain.model.CompanyListing
import com.sacnoift.stockmartapp.domain.model.IntraDayInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import java.util.Calendar.MONDAY
import java.util.Calendar.SUNDAY
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.days

/**
 * Created by Patrick Adutwum on 14/12/2022.
 */

@Singleton
class IntraDayInfoParser @Inject constructor(): CSVParser<IntraDayInfo> {
    override suspend fun parse(stream: InputStream): List<IntraDayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO){
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val timestamp = line.getOrNull(0) ?: return@mapNotNull null
                    val close = line.getOrNull(4) ?: return@mapNotNull null
                    val dto = IntraDayInfoDto(timestamp, close.toDouble())
                    dto.toIntraDayInfo()
                }
                .filter {
                    val calender = Calendar.getInstance()
                    var daysToSub = when (calender.get(Calendar.DAY_OF_WEEK)) {
                        SUNDAY -> 2
                        MONDAY -> 3
                        else -> 1
                    }
                    it.date.dayOfMonth == LocalDate.now().minusDays(daysToSub.toLong()).dayOfMonth
                }
                .sortedBy {
                    it.date.hour
                }
                .also {
                 csvReader.close()
             }
        }
    }
}