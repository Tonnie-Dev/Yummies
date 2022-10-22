package com.uxstate.yummies.data.repository

import com.uxstate.yummies.data.local.YummiesDatabase
import com.uxstate.yummies.data.remote.YummiesAPI
import com.uxstate.yummies.domain.model.Category
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.repository.YummiesRepository
import com.uxstate.yummies.util.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class YummiesRepositoryImpl @Inject constructor(
    private val api: YummiesAPI,
    private val db: YummiesDatabase
) : YummiesRepository {
    override fun fetchMealItems(
        query: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<Meal>>> {
        TODO("Not yet implemented")
    }

    override fun fetchCategoriesItems(): Flow<Resource<List<Category>>> {
        TODO("Not yet implemented")
    }
}