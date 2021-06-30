package com.duonghb.testbitrise.domain.model

import com.google.gson.annotations.SerializedName

data class VerticalModel(

    @field:SerializedName("data")
    val data: DataVertical,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Int
)

data class ListItemVertical(

    @field:SerializedName("question8enable")
    val question8enable: Int,

    @field:SerializedName("category_name")
    val categoryName: String,

    @field:SerializedName("gender")
    val gender: Int,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("open_count")
    val openCount: Int,

    @field:SerializedName("question5")
    val question5: String,

    @field:SerializedName("question4")
    val question4: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("is_sent")
    val isSent: Int,

    @field:SerializedName("age_from")
    val ageFrom: Int,

    @field:SerializedName("updated_at")
    val updatedAt: String,

    @field:SerializedName("category_id")
    val categoryId: Int,

    @field:SerializedName("is_push")
    val isPush: Int,

    @field:SerializedName("question7")
    val question7: String,

    @field:SerializedName("question6")
    val question6: String,

    @field:SerializedName("question9")
    val question9: String,

    @field:SerializedName("question8")
    val question8: String,

    @field:SerializedName("logo")
    val logo: Int,

    @field:SerializedName("question6enable")
    val question6enable: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("age_to")
    val ageTo: Int,

    @field:SerializedName("published_at")
    val publishedAt: String,

    @field:SerializedName("is_push_date")
    val isPushDate: Int,

    @field:SerializedName("is_filter")
    val isFilter: Int,

    @field:SerializedName("is_member")
    val isMember: Int,

    @field:SerializedName("question9enable")
    val question9enable: Int,

    @field:SerializedName("question4enable")
    val question4enable: Int,

    @field:SerializedName("push_count")
    val pushCount: Int,

    @field:SerializedName("question5enable")
    val question5enable: Int,

    @field:SerializedName("is_type")
    val isType: Int,

    @field:SerializedName("datenotify")
    val datenotify: String,

    @field:SerializedName("is_base64")
    val isBase64: Boolean,

    @field:SerializedName("image_path")
    val imagePath: String,

    @field:SerializedName("subtitle")
    val subtitle: String,

    @field:SerializedName("question7enable")
    val question7enable: Int,

    @field:SerializedName("datestartapp")
    val datestartapp: Int
)

data class DataVertical(

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("time")
    val time: String,

    @field:SerializedName("list")
    val list: List<ListItemVertical>
)
