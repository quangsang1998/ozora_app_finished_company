package com.duonghb.testbitrise.data.repository

import com.duonghb.testbitrise.data.network.ApiService
import com.duonghb.testbitrise.domain.model0.HorizontalModel
import com.duonghb.testbitrise.domain.model1.VerticalModel
import com.duonghb.testbitrise.domain.result.Result
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
  private val apiService: ApiService
) {
  fun getNewsListVertical(apiKey: String): Deferred<List<VerticalModel>> {
      return apiService.getList0(apiKey)
  }

  fun getNewsListHorizontal(apiKey: String): Deferred<List<HorizontalModel>> {
      return apiService.getList1(apiKey)
  }
}