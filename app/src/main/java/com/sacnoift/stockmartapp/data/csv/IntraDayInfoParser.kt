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
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

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
                    it.date.dayOfMonth == LocalDateTime.now().minusDays(1).dayOfMonth
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