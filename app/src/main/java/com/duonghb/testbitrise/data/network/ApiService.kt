package com.duonghb.testbitrise.data.network

import com.duonghb.testbitrise.domain.model.HorizontalModel
import com.duonghb.testbitrise.domain.model.LargeCategoryModel
import com.duonghb.testbitrise.domain.model.SubCategoryModel
import com.duonghb.testbitrise.domain.model.SubContentModel
import com.duonghb.testbitrise.domain.model.VerticalModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("news")
    suspend fun getListHorizontal(
        @Field("client_id") client_id: Int,
        @Field("device_token") device_token: String?,
        @Field("user_id") user_id: Int?,
        @Field("is_type") is_type: Int,
        @Field("limit") limit: Int,
        @Field("offset") offset: Int
    ): HorizontalModel

    @FormUrlEncoded
    @POST("news")
    suspend fun getListVertical(
        @Field("client_id") client_id: Int,
        @Field("device_token") device_token: String?,
        @Field("user_id") user_id: Int?,
        @Field("is_type") is_type: Int,
        @Field("limit") limit: Int,
        @Field("offset") offset: Int
    ): VerticalModel

    @FormUrlEncoded
    @POST("content/category")
    suspend fun getListLargeCategories(
        @Field("client_id") client_id: Int,
    ): LargeCategoryModel

    @FormUrlEncoded
    @POST("content/category/sub")
    suspend fun getListSubCategories(
        @Field("client_id") client_id: Int,
        @Field("id") id: Int,
    ): SubCategoryModel

    @FormUrlEncoded
    @POST("content/category/sub/item")
    suspend fun getListSubContents(
        @Field("client_id") client_id: Int,
        @Field("category_id") category_id: Int,
        @Field("subcategory_id") subcategory_id: Int?,
        @Field("limit") limit: Int,
        @Field("offset") offset: Int
    ): SubContentModel
}
