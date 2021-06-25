package com.duonghb.testbitrise.domain.model

import com.google.gson.annotations.SerializedName

data class HorizontalModel(

    @field:SerializedName("data")
    val data: DataHorizontal? = null,

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Int? = null
)

data class DataHorizontal(

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("time")
    val time: String? = null,

    @field:SerializedName("list")
    val list: List<ListItemHorizontal?>? = null
)

data class ListItemHorizontal(

    @field:SerializedName("question8enable")
    val question8enable: Int? = null,

    @field:SerializedName("category_name")
    val categoryName: String? = null,

    @field:SerializedName("gender")
    val gender: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("open_count")
    val openCount: Int? = null,

    @field:SerializedName("question5")
    val question5: String? = null,

    @field:SerializedName("question4")
    val question4: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("is_sent")
    val isSent: Int? = null,

    @field:SerializedName("age_from")
    val ageFrom: Int? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("category_id")
    val categoryId: Int? = null,

    @field:SerializedName("is_push")
    val isPush: Int? = null,

    @field:SerializedName("question7")
    val question7: String? = null,

    @field:SerializedName("question6")
    val question6: String? = null,

    @field:SerializedName("question9")
    val question9: String? = null,

    @field:SerializedName("question8")
    val question8: String? = null,

    @field:SerializedName("logo")
    val logo: Int? = null,

    @field:SerializedName("question6enable")
    val question6enable: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("age_to")
    val ageTo: Int? = null,

    @field:SerializedName("published_at")
    val publishedAt: String? = null,

    @field:SerializedName("is_push_date")
    val isPushDate: Int? = null,

    @field:SerializedName("is_filter")
    val isFilter: Int? = null,

    @field:SerializedName("is_member")
    val isMember: Int? = null,

    @field:SerializedName("question9enable")
    val question9enable: Int? = null,

    @field:SerializedName("question4enable")
    val question4enable: Int? = null,

    @field:SerializedName("push_count")
    val pushCount: Int? = null,

    @field:SerializedName("question5enable")
    val question5enable: Int? = null,

    @field:SerializedName("is_type")
    val isType: Int? = null,

    @field:SerializedName("datenotify")
    val datenotify: String? = null,

    @field:SerializedName("image_path")
    val imagePath: String? = null,

    @field:SerializedName("subtitle")
    val subtitle: String? = null,

    @field:SerializedName("question7enable")
    val question7enable: Int? = null,

    @field:SerializedName("datestartapp")
    val datestartapp: Int? = null
)
