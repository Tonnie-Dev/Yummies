package com.uxstate.yummies.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uxstate.yummies.data.local.YummiesDatabase
import com.uxstate.yummies.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//provide database



    @Provides
    @Singleton

    fun provideDatabase(@ApplicationContext context:Context):YummiesDatabase {


        return Room.databaseBuilder(context,YummiesDatabase::class.java, Constants.DATABASE_NAME).build()
    }
}