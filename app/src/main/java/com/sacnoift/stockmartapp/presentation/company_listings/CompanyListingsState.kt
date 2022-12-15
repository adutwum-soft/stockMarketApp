package com.sacnoift.stockmartapp.presentation.company_listings

import com.sacnoift.stockmartapp.domain.model.CompanyListing

/**
 * Created by Patrick Adutwum on 15/12/2022.
 */
data class CompanyListingsState(
	val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
)
