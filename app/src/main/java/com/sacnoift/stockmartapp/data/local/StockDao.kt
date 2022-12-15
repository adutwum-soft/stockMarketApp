package com.sacnoift.stockmartapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Patrick Adutwum on 14/12/2022.
 */

@Dao
interface StockDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertCompanyListings(
		 companyListingEntities: List<CompanyListingEntity>
	)

	@Query("DELETE FROM companyListingEntity")
	suspend fun clearCompanyListings()

    @Query(
              """
               SELECT *
               FROM companyListingEntity
               WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                    UPPER(:query) == symbol
            """
    )
	suspend fun searchCompanyListing(query: String): List<CompanyListingEntity>
}