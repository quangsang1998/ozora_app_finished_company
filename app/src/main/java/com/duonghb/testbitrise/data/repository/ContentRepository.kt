package com.duonghb.testbitrise.data.repository

import com.duonghb.testbitrise.data.network.ApiService
import com.duonghb.testbitrise.domain.model.ListItemLargeCategory
import com.duonghb.testbitrise.domain.model.ListItemSubCategory
import com.duonghb.testbitrise.domain.model.TabContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getLargeCategories(client_id: Int): Flow<List<ListItemLargeCategory>> {
        return flow {
            val list = apiService.getListLargeCategories(client_id)
            emit(list.data.list)
        }
    }

    suspend fun getSubCategories(client_id: Int, id: Int): Flow<List<ListItemSubCategory>> {
        return flow {
            val list = apiService.getListSubCategories(client_id, id)
            emit(list.data.list)
        }
    }

    suspend fun getSubContents(
        client_id: Int,
        category_id: Int,
        subCategory: ListItemSubCategory?,
        limit: Int,
        offset: Int
    ): Flow<TabContent> {
        return flow {
            val list = apiService.getListSubContents(
                client_id,
                category_id,
                subCategory?.id,
                limit,
                offset
            )
            emit(TabContent(subCategory, list.data.list))
        }
    }
}
