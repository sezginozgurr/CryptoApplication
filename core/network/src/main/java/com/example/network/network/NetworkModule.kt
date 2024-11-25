package com.example.network.network


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun getInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request()
                .newBuilder()
                .build()
            it.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.btcturk.com/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}