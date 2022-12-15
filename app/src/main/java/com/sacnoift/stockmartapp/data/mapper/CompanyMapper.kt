package com.sacnoift.stockmartapp.data.mapper

import com.sacnoift.stockmartapp.data.local.CompanyListingEntity
import com.sacnoift.stockmartapp.data.remote.dto.CompanyInfoDto
import com.sacnoift.stockmartapp.domain.model.CompanyInfo
import com.sacnoift.stockmartapp.domain.model.CompanyListing

/**
 * Created by Patrick Adutwum on 14/12/2022.
 */

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity{
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo{
    return CompanyInfo(
        symbol = symbol ?: "",
        assetType = assetType ?: "",
        name = name ?: "",
        description = description ?: "",
        currency = currency ?: "",
        country = country ?: "",
        sector = sector ?: "",
        industry = industry ?: "",
        address = address ?: ""
    )
}