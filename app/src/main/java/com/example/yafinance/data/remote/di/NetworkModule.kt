package com.example.yafinance.data.remote.di

import android.content.Context
import com.example.yafinance.R
import com.example.yafinance.data.remote.api.FinanceApi
import com.example.yafinance.data.remote.repositories.AccountRepositoryImpl
import com.example.yafinance.domain.repositories.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://shmr-finance.ru/api/v1/"

    @Singleton
    @Provides
    fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()

                val request = original.newBuilder()
                    .header("Authorization", context.getString(R.string.token))
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
    fun provideFinanceApi(retrofit: Retrofit): FinanceApi =
        retrofit.create(FinanceApi::class.java)

    @Singleton
    @Provides
    fun provideAccountRepository(
        financeApi: FinanceApi
    ): AccountRepository =
        AccountRepositoryImpl(
            financeApi = financeApi
        )
}