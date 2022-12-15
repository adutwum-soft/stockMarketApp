package com.sacnoift.stockmartapp.di

import com.sacnoift.stockmartapp.data.csv.CSVParser
import com.sacnoift.stockmartapp.data.csv.CompanyListingsParser
import com.sacnoift.stockmartapp.data.repository.StockRepositoryImp
import com.sacnoift.stockmartapp.domain.model.CompanyListing
import com.sacnoift.stockmartapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Patrick Adutwum on 15/12/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindsStockRepository(
        stockRepositoryImp: StockRepositoryImp
    ): StockRepository
}