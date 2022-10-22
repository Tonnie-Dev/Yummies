package com.uxstate.yummies.data.repository

import androidx.room.withTransaction
import com.uxstate.yummies.data.local.YummiesDatabase
import com.uxstate.yummies.data.mapper.toModel
import com.uxstate.yummies.data.remote.YummiesAPI
import com.uxstate.yummies.domain.model.Category
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.repository.YummiesRepository
import com.uxstate.yummies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class YummiesRepositoryImpl @Inject constructor(
    private val api: YummiesAPI,
    private val db: YummiesDatabase
) : YummiesRepository {

    val dao = db.dao
    override fun fetchMealItems(
        query: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Meal>>> = flow {

        //Emit Loading
        emit(Resource.Loading(loading = true))

        //Query database and emit
        val localMeals = dao.getMealItems(query = query)
        emit(Resource.Success(data = localMeals.map { it.toModel() }))

        //Determine if API Call is needed

        val isFetchFromCache = (!fetchFromRemote && localMeals.isEmpty())

        // go local
        emit(Resource.Loading(loading = false))

        //go remote
        val remoteMeals = api.getMealsByName(query)

        db.withTransaction {

            dao.clearMeals()
            dao.insertMeals(remoteMeals.mealDTOS.map { it.toModel() })
        }

    }

    override fun fetchCategoriesItems(): Flow<Resource<List<Category>>> {
        TODO("Not yet implemented")
    }
}