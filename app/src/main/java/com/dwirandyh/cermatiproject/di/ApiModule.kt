package com.dwirandyh.cermatiproject.di

import android.app.Application
import android.content.Context
import com.dwirandyh.cermatiproject.BuildConfig
import com.dwirandyh.cermatiproject.data.remote.NetworkInterceptor
import com.dwirandyh.cermatiproject.data.remote.UserEndpoint
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.internal.cache.CacheInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(applicationContext: Context): Retrofit {
        val httpCacheDirectory = File(applicationContext.cacheDir, "http-cache")
        val cacheSize: Long = 10 * 2014 * 1024
        val cache = Cache(httpCacheDirectory, cacheSize)

        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(NetworkInterceptor())
            .cache(cache)
            .build()


        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideUserEndpoint(retrofit: Retrofit): UserEndpoint =
        retrofit.create(UserEndpoint::class.java)
}