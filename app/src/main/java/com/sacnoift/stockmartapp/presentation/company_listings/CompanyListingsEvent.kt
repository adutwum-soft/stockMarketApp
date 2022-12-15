package com.sacnoift.stockmartapp.presentation.company_listings

/**
 * Created by Patrick Adutwum on 15/12/2022.
 */
sealed class CompanyListingsEvent{
    object Refresh: CompanyListingsEvent()
    data class OnSearchQueryChanged(val query: String): CompanyListingsEvent()
}
