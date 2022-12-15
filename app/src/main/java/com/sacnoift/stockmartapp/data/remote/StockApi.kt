package com.sacnoift.stockmartapp.data.remote

import com.sacnoift.stockmartapp.data.remote.dto.CompanyInfoDto
import com.sacnoift.stockmartapp.util.Constants
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Patrick Adutwum on 14/12/2022.
 */
interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apikey: String = Constants.API_KEY
    ): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntraDayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apikey: String = Constants.API_KEY
    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apikey: String = Constants.API_KEY
    ): CompanyInfoDto
}