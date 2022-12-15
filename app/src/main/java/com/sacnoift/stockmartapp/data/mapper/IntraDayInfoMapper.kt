package com.sacnoift.stockmartapp.data.mapper

import com.sacnoift.stockmartapp.data.remote.dto.IntraDayInfoDto
import com.sacnoift.stockmartapp.domain.model.IntraDayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by Patrick Adutwum on 15/12/2022.
 */

fun IntraDayInfoDto.toIntraDayInfo(): IntraDayInfo{
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return IntraDayInfo(
        date = localDateTime,
        close = close
    )
}