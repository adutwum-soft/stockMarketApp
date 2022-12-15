package com.sacnoift.stockmartapp.data.remote

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
}