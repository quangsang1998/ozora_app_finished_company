package com.duonghb.testbitrise.domain.usecase

import com.duonghb.testbitrise.data.repository.NewsRepositoryImpl
import com.duonghb.testbitrise.domain.model.HorizontalModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsListHorizontalUseCase @Inject constructor(
    private val newsRepositoryImpl: NewsRepositoryImpl
) {
    suspend operator fun invoke(
        client_id: Int,
        device_token: String?,
        user_id: Int?,
        is_type: Int,
        limit: Int,
        offset: Int
    ): Flow<HorizontalModel> {
        return newsRepositoryImpl.getNewsListHorizontal(
            client_id, device_token, user_id, is_type, limit, offset
        )
    }
}
