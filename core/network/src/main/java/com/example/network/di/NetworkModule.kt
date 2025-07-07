package com.example.network.di

import com.example.network.BuildConfig
import com.example.network.api.AccountApi
import com.example.network.api.CategoryApi
import com.example.network.api.TransactionApi
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    private const val BASE_URL = "https://shmr-finance.ru/api/v1/"

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()

                val request = original.newBuilder()
                    .header("Authorization", BuildConfig.API_TOKEN)
                    .method(original.method, original.body)
                    .build()

                return chain.proceed(request)
            }
        })

        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(httpClient: OkHttpClient): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()
        val converterFactory = json.asConverterFactory(contentType)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideTransactionApi(retrofit: Retrofit): TransactionApi =
        retrofit.create(TransactionApi::class.java)

    @Singleton
    @Provides
    fun provideCategoryApi(retrofit: Retrofit): CategoryApi =
        retrofit.create(CategoryApi::class.java)

    @Singleton
    @Provides
    fun provideAccountApi(retrofit: Retrofit): AccountApi =
        retrofit.create(AccountApi::class.java)

}