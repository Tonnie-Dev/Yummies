package com.uxstate.yummies.data.repository

import com.uxstate.yummies.data.local.YummiesDatabase
import com.uxstate.yummies.data.mapper.toEntity
import com.uxstate.yummies.data.mapper.toModel
import com.uxstate.yummies.data.remote.YummiesAPI
import com.uxstate.yummies.domain.model.Category
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.repository.YummiesRepository
import com.uxstate.yummies.util.Resource
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber

// force single instance of our repository impl for the entire app
@Singleton

/*
Use @Inject so that Hilt knows how to create CountryRepositoryImpl object
noting we will be injecting the interface and not the impl
*/

// always favor/depend on abstractions instead of concrete classes
class YummiesRepositoryImpl @Inject constructor(
    private val api: YummiesAPI,
    private val db: YummiesDatabase
) : YummiesRepository {

    private val dao = db.dao
    override fun fetchMealItems(
        query: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Meal>>> = flow {

        // Emit Loading status true
        emit(Resource.Loading(loading = true))

        // Query database and emit immediately
        val localMeals = dao.getMealItems(query = query)
        emit(Resource.Success(data = localMeals.map { it.toModel() }))

        // Determine if API Call is needed

        val isFetchFromCache = (!fetchFromRemote && localMeals.isNotEmpty())

        if (isFetchFromCache) {
            // go local
            emit(Resource.Loading(loading = false))

            // return control to @flow
            return@flow
        }

        // go remote
        val remoteMeals = try {
            api.getMealsByName(query)
        } catch (e: HttpException) {

            emit(
                Resource.Error(
                    errorMessage = "Unknown Error Occurred, Please try again",
                    data = null
                )
            )
            null
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    errorMessage = "Could not load data, please check your internet connection",
                    data = null
                )
            )
            null
        }

        // clear and re-populate the database

        dao.clearMeals()

        remoteMeals?.let {

            dao.insertMeals(remoteMeals.mealDTOS.map { dto -> dto.toEntity() })
        }

        val updatedCache = dao.getMealItems(query = query)
        emit(Resource.Success(data = updatedCache.map { it.toModel() }))

        emit(Resource.Loading(loading = false))
    }

    override fun fetchCategoriesItems(): Flow<Resource<List<Category>>> = flow {

        // show loading
        emit(Resource.Loading(loading = true))

        // Query Database and Emit immediately

        val localCategories = dao.getCategoriesItems()
        emit(Resource.Success(data = localCategories.map { it.toModel() }))
        Timber.i("After Querying the db size is ${localCategories.size}")
        // Determine if API Call is needed
        val fetchJustFromCache = localCategories.isNotEmpty()

        if (fetchJustFromCache) {

            Timber.i("Enter justFetchFromCache if-block")
            // Go Local
            emit(Resource.Loading(loading = false))

            // return control to @flow
            return@flow
        }

        // Go Remote
        Timber.i("Going remote")
        val remoteCategories = try {

            api.getCategories()
        } catch (e: HttpException) {

            emit(
                Resource.Error(errorMessage = "Unknown Error Occurred, Please try again")
            )

            null
        } catch (e: IOException) {

            emit(
                Resource.Error(
                    errorMessage = """
                    Could not load data, please check your internet connection
                    """.trimIndent()
                )
            )

            null
        }

        // clear and re-populate database

        dao.clearCategories()
        Timber.i("Db deleted, size is${localCategories.size}")
        remoteCategories?.let {

            dao.insertCategories(it.categories.map { dto -> dto.toEntity() })
        }

        val updatedCache = dao.getCategoriesItems()

        Timber.i("New Records inserted - new size is ${updatedCache.size}")
        emit(Resource.Success(data = updatedCache.map { it.toModel() }))

        // stop loading
        emit(Resource.Loading(loading = false))
    }
}