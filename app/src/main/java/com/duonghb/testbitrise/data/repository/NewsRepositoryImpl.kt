package com.duonghb.testbitrise.data.repository

import com.duonghb.testbitrise.data.network.ApiService
import com.duonghb.testbitrise.domain.model.HorizontalModel
import com.duonghb.testbitrise.domain.model.VerticalModel
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
    ): HorizontalModel {
        return apiService.getListHorizontal(client_id, device_token, user_id, is_type, limit, offset)
    }

    suspend fun getNewsListVertical(
        client_id: Int,
        device_token: String?,
        user_id: Int?,
        is_type: Int,
        limit: Int,
        offset: Int
    ): VerticalModel {
        return apiService.getListVertical(client_id, device_token, user_id, is_type, limit, offset)
    }
}
