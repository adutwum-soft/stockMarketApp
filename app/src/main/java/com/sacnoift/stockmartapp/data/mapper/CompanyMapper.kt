package com.sacnoift.stockmartapp.data.mapper

import com.sacnoift.stockmartapp.data.local.CompanyListingEntity
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