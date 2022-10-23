package com.uxstate.yummies.di

import com.uxstate.yummies.data.repository.YummiesRepositoryImpl
import com.uxstate.yummies.domain.repository.YummiesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton//@Binds used for 1-to-1 interface-implementation mapping


    /*The abstract function takes only a single parameter which
     is the interface implementation and the return type is the
     interface implemented by the given parameter object.*/

    abstract fun bindYummiesRepository(repositoryImpl: YummiesRepositoryImpl):YummiesRepository


}