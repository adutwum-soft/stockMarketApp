package com.sacnoift.stockmartapp.di

import android.app.Application
import androidx.room.Room
import com.sacnoift.stockmartapp.data.local.StockDatabase
import com.sacnoift.stockmartapp.data.remote.StockApi
import com.sacnoift.stockmartapp.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Patrick Adutwum on 15/12/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val logging = HttpLoggingInterceptor()

    private val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(
            Interceptor{ chain ->
                val builder = chain.request().newBuilder()
                return@Interceptor chain.proceed(builder.build())
            }
        )
        logging.level = HttpLoggingInterceptor.Level.BODY
        addNetworkInterceptor(logging)
    }.build()

	@Provides
    @Singleton
    fun providesStockApi(): StockApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
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