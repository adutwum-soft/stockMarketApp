package com.sacnoift.stockmartapp.data.repository

import com.sacnoift.stockmartapp.data.csv.CSVParser
import com.sacnoift.stockmartapp.data.csv.CompanyListingsParser
import com.sacnoift.stockmartapp.data.local.StockDatabase
import com.sacnoift.stockmartapp.data.mapper.toCompanyInfo
import com.sacnoift.stockmartapp.data.mapper.toCompanyListing
import com.sacnoift.stockmartapp.data.mapper.toCompanyListingEntity
import com.sacnoift.stockmartapp.data.remote.StockApi
import com.sacnoift.stockmartapp.data.remote.dto.CompanyInfoDto
import com.sacnoift.stockmartapp.domain.model.CompanyInfo
import com.sacnoift.stockmartapp.domain.model.CompanyListing
import com.sacnoift.stockmartapp.domain.model.IntraDayInfo
import com.sacnoift.stockmartapp.domain.repository.StockRepository
import com.sacnoift.stockmartapp.util.Constants
import com.sacnoift.stockmartapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Patrick Adutwum on 14/12/2022.
 */

@Singleton
class StockRepositoryImp @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingsParser: CSVParser<CompanyListing>,
    private val intraDayInfoParser: CSVParser<IntraDayInfo>
): StockRepository {
    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListing = dao.searchCompanyListing(query)
            emit(Resource.Success(
             data = localListing.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache){
                 emit(Resource.Loading(false))
                 return@flow
            }

            val remoteListing = try {
                val response = api.getListings(Constants.API_KEY)
                companyListingsParser.parse(response.byteStream())
            }catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }catch (e: HttpException){
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListing?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(listings.map { it.toCompanyListingEntity() })
                emit(Resource.Success(
                    data = dao
                        .searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getIntraDayInfo(symbol: String): Resource<List<IntraDayInfo>> {
        return try {
            val response = api.getIntraDayInfo(symbol = symbol)
            val result = intraDayInfoParser.parse(response.byteStream())
            Resource.Success(result)
        }catch (e: IOException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intra day info"
            )
        }catch (e: HttpException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intra day info"
            )
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            val result = api.getCompanyInfo(symbol = symbol)
            Resource.Success(result.toCompanyInfo())
        }catch (e: IOException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load Company info for $symbol"
            )
        }catch (e: HttpException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load Company info for $symbol"
            )
        }
    }
}