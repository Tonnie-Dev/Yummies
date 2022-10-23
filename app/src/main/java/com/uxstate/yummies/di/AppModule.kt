package com.uxstate.yummies.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.uxstate.yummies.data.local.YummiesDatabase
import com.uxstate.yummies.data.remote.YummiesAPI
import com.uxstate.yummies.util.Constants
import com.uxstate.yummies.util.Constants.CONNECT_TIMEOUT
import com.uxstate.yummies.util.Constants.READ_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // provide database



    @Provides
    @Singleton

    fun provideDatabase(@ApplicationContext context: Context): YummiesDatabase {

        return Room.databaseBuilder(
                context,
                YummiesDatabase::class.java,
                Constants.DATABASE_NAME
        )
                .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton

    fun provideApiService(): YummiesAPI {


        val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

        return Retrofit.Builder()
                .baseUrl(YummiesAPI.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create()
    }
}