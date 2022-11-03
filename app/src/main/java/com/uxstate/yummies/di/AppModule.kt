package com.uxstate.yummies.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.uxstate.yummies.data.local.YummiesDatabase
import com.uxstate.yummies.data.remote.YummiesAPI
import com.uxstate.yummies.domain.repository.YummiesRepository
import com.uxstate.yummies.domain.use_cases.*
import com.uxstate.yummies.util.Constants
import com.uxstate.yummies.util.Constants.CONNECT_TIMEOUT
import com.uxstate.yummies.util.Constants.READ_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    /*For debugging purposes it’s nice to have a log feature integrated to
    show request and response information. */

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {

            // set level
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {

        /* connect timeout defines a time period in which our
         client should establish a connection with a target host.
    By default, for the OkHttpClient, this timeout is set to 10 seconds.   */

        /*maximum time of inactivity between two data packets when waiting for the
        server's response.The default timeout of 10 seconds */
        return OkHttpClient.Builder()
                .addInterceptor(interceptor) // activate interceptor
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) // find a relationship
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS) // courtship
                .build()
    }

    @Provides
    @Singleton

    fun provideApiService(okHttpClient: OkHttpClient): YummiesAPI {

        val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

        return Retrofit.Builder()
                .baseUrl(YummiesAPI.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
                .create()
    }

    // provide use case container
    @Provides
    @Singleton
    fun provideUseCaseContainer(repository: YummiesRepository): UseCaseContainer {

        return UseCaseContainer(
                getMealsUseCase = GetMealsUseCase(repository = repository),
                getCategoriesUseCase = GetCategoriesUseCase(repository = repository),
                getMealsByCategoryUseCase = GetMealsByCategoryUseCase(repository = repository),
                updateStarUseCase = UpdateStarUseCase(repository = repository),
                starUseCase = StarUseCase(repository = repository),
                unStarUseCase = UnStarUseCase(repository = repository)
        )
    }
}