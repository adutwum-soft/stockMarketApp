package com.sacnoift.stockmartapp.data.remote.dto

import com.squareup.moshi.Json

/**
 * Created by Patrick Adutwum on 15/12/2022.
 */
data class CompanyInfoDto(
    @field:Json(name = "Symbol") val symbol: String?,
    @field:Json(name = "AssetType") val assetType: String?,
    @field:Json(name = "Name") val name: String?,
    @field:Json(name = "Description") val description: String?,
    @field:Json(name = "Currency") val currency: String?,
    @field:Json(name = "Country") val country: String?,
    @field:Json(name = "Sector") val sector: String?,
    @field:Json(name = "Industry") val industry: String?,
    @field:Json(name = "Address") val address: String?,
)
