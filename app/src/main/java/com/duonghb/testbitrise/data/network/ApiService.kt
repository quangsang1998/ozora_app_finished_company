package com.duonghb.testbitrise.data.network

import com.duonghb.testbitrise.constant.Constant
import com.duonghb.testbitrise.domain.model0.HorizontalModel
import com.duonghb.testbitrise.domain.model1.VerticalModel
import com.duonghb.testbitrise.domain.result.Result
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constant.GET_NEWS_lIST_0)
    fun getList0(@Query(Constant.API_KEY_0) apiKey: String): Deferred<Result<VerticalModel>>

    @GET(Constant.GET_NEWS_LIST_1)
    fun getList1(@Query(Constant.API_KEY_1) apiKey: String): Deferred<Result<HorizontalModel>>
}
