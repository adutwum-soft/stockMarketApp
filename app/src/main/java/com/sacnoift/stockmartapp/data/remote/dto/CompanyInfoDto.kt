package com.sacnoift.stockmartapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

/**
 * Created by Patrick Adutwum on 15/12/2022.
 */
data class CompanyInfoDto(
    @SerializedName("Symbol") val symbol: String?,
    @SerializedName("AssetType") val assetType: String?,
    @SerializedName("Name") val name: String?,
    @SerializedName("Description") val description: String?,
    @SerializedName("Currency") val currency: String?,
    @SerializedName("Country") val country: String?,
    @SerializedName("Sector") val sector: String?,
    @SerializedName("Industry") val industry: String?,
    @SerializedName("Address") val address: String?,
)
