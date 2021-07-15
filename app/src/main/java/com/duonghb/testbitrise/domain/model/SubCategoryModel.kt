package com.duonghb.testbitrise.domain.model

import com.google.gson.annotations.SerializedName

data class SubCategoryModel(

    @field:SerializedName("data")
    val data: DataSubCategory,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Int
)

data class ListItemSubCategory(

    @field:SerializedName("category_id")
    val categoryId: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)

data class DataSubCategory(

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("list")
    val list: List<ListItemSubCategory>
)
