package com.sacnoift.stockmartapp.domain.model

import java.time.LocalDateTime

/**
 * Created by Patrick Adutwum on 15/12/2022.
 */
data class IntraDayInfo(
    val date: LocalDateTime,
    val close: Double,
)
