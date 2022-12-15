package com.sacnoift.stockmartapp.presentation.company_info

import com.sacnoift.stockmartapp.domain.model.CompanyInfo
import com.sacnoift.stockmartapp.domain.model.IntraDayInfo

/**
 * Created by Patrick Adutwum on 15/12/2022.
 */
data class CompanyInfoState(
    val stockInfos: List<IntraDayInfo> = emptyList(),
    val companyInfo: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
