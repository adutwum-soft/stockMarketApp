package com.sacnoift.stockmartapp.domain.repository

import com.sacnoift.stockmartapp.domain.model.CompanyListing
import com.sacnoift.stockmartapp.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Patrick Adutwum on 14/12/2022.
 */
interface StockRepository {
	suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
	): Flow<Resource<List<CompanyListing>>>
}