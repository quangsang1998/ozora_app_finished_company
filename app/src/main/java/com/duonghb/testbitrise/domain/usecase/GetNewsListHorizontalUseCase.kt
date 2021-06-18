package com.duonghb.testbitrise.domain.usecase

import com.duonghb.testbitrise.data.repository.NewsRepositoryImpl
import com.duonghb.testbitrise.domain.model0.HorizontalModel
import com.duonghb.testbitrise.domain.model1.VerticalModel
import com.duonghb.testbitrise.domain.result.Result
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsListHorizontalUseCase @Inject constructor(
    private val newsRepositoryImpl: NewsRepositoryImpl
) {
    operator fun invoke(apiKey: String): Deferred<Result<HorizontalModel>> {
        return newsRepositoryImpl.getNewsListHorizontal(apiKey)
    }
}
