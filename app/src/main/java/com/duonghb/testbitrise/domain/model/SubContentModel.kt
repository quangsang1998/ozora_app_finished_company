package com.duonghb.testbitrise.domain.model

import com.google.gson.annotations.SerializedName

data class SubContentModel(

    @field:SerializedName("data")
    val data: DataSubContent,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Int
)

data class ListItemSubContent(

    @field:SerializedName("thumbnail_type")
    val thumbnailType: Int,

    @field:SerializedName("thumbnail")
    val thumbnail: String,

    @field:SerializedName("subtitle")
    val subtitle: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String
)

data class DataSubContent(

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("list")
    val list: List<ListItemSubContent>
)
