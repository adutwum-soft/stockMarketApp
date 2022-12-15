package com.sacnoift.stockmartapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Patrick Adutwum on 14/12/2022.
 */

@Database(
	 entities = [CompanyListingEntity::class],
     version = 1
)
abstract class StockDatabase: RoomDatabase() {
   abstract val dao: StockDao
}