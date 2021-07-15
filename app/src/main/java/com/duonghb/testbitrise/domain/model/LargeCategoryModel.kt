package com.duonghb.testbitrise.domain.model

import com.google.gson.annotations.SerializedName

data class LargeCategoryModel(

    @field:SerializedName("data")
    val data: DataLargeCategory,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Int
)

data class ListItemLargeCategory(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)

data class DataLargeCategory(

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("list")
    val list: List<ListItemLargeCategory>
)
