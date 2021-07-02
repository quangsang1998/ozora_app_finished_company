package com.duonghb.testbitrise.data.network

import com.duonghb.testbitrise.domain.model.HorizontalModel
import com.duonghb.testbitrise.domain.model.VerticalModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("news")
    suspend fun getList0(
        @Field("client_id") client_id: Int,
        @Field("device_token") device_token: String?,
        @Field("user_id") user_id: Int?,
        @Field("is_type") is_type: Int,
        @Field("limit") limit: Int,
        @Field("offset") offset: Int
    ): HorizontalModel

    @FormUrlEncoded
    @POST("news")
    suspend fun getList1(
        @Field("client_id") client_id: Int,
        @Field("device_token") device_token: String?,
        @Field("user_id") user_id: Int?,
        @Field("is_type") is_type: Int,
        @Field("limit") limit: Int,
        @Field("offset") offset: Int
    ): VerticalModel
}
