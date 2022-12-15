package com.sacnoift.stockmartapp.domain.model

import com.squareup.moshi.Json

/**
 * Created by Patrick Adutwum on 15/12/2022.
 */
data class CompanyInfo(
    val symbol: String,
    val assetType: String,
    val name: String,
    val description: String,
    val currency: String,
    val country: String,
    val sector: String,
    val industry: String,
    val address: String,
)
