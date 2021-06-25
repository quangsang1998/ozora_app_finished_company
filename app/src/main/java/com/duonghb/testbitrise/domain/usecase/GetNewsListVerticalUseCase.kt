package com.duonghb.testbitrise.domain.usecase

import com.duonghb.testbitrise.data.repository.NewsRepositoryImpl
import com.duonghb.testbitrise.domain.model.VerticalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsListVerticalUseCase @Inject constructor(
    private val newsRepositoryImpl: NewsRepositoryImpl
) {
    suspend operator fun invoke(
        client_id: Int,
        device_token: String?,
        user_id: Int?,
        is_type: Int,
        limit: Int,
        offset: Int
    ): VerticalModel {
        return withContext(Dispatchers.Default) {
            newsRepositoryImpl.getNewsListVertical(
                client_id, device_token, user_id, is_type, limit, offset
            )
        }
    }
}
