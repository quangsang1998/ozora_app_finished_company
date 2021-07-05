package com.duonghb.testbitrise.data.repository

import com.duonghb.testbitrise.data.network.ApiService
import com.duonghb.testbitrise.domain.model.HorizontalModel
import com.duonghb.testbitrise.domain.model.VerticalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getNewsListHorizontal(
        client_id: Int,
        device_token: String?,
        user_id: Int?,
        is_type: Int,
        limit: Int,
        offset: Int
    ): Flow<HorizontalModel> {
        return flow {
            val list = apiService.getListHorizontal(
                client_id, device_token, user_id, is_type, limit, offset
            )
            emit(list)
        }
    }

    suspend fun getNewsListVertical(
        client_id: Int,
        device_token: String?,
        user_id: Int?,
        is_type: Int,
        limit: Int,
        offset: Int
    ): Flow<VerticalModel> {
        return flow {
            val list = apiService.getListVertical(
                client_id, device_token, user_id, is_type, limit, offset
            )
            emit(list)
        }
    }
}
