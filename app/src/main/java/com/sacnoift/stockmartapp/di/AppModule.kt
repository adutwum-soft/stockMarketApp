package com.sacnoift.stockmartapp.di

import android.app.Application
import androidx.room.Room
import com.sacnoift.stockmartapp.data.local.StockDatabase
import com.sacnoift.stockmartapp.data.remote.StockApi
import com.sacnoift.stockmartapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Patrick Adutwum on 15/12/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	@Provides
    @Singleton
    fun providesStockApi(): StockApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(StockApi::class.java)
    }

    @Provides
    @Singleton
    fun providesStockDatabase(app: Application): StockDatabase{
        return Room.databaseBuilder(
            app,
            StockDatabase::class.java,
            "stockdb.db"
        ).build()
    }
}