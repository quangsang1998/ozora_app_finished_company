package com.duonghb.testbitrise.domain.usecase

import com.duonghb.testbitrise.data.repository.NewsRepositoryImpl
import com.duonghb.testbitrise.domain.model1.VerticalModel
import com.duonghb.testbitrise.domain.result.Result
import kotlinx.coroutines.Deferred

class GetNewsListVerticalUseCase(
  private val newsRepositoryImpl: NewsRepositoryImpl
) {
  operator fun invoke(apiKey: String): Deferred<List<VerticalModel>> {
    return newsRepositoryImpl.getNewsListVertical(apiKey)
  }
}