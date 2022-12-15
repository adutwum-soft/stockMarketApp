package com.sacnoift.stockmartapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Patrick Adutwum on 14/12/2022.
 */

@Entity
data class CompanyListingEntity(
    val name: String,
    val symbol: String,
    val exchange: String,
    @PrimaryKey val id: Int? = null
)
