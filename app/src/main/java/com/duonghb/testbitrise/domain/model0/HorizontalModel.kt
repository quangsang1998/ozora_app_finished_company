package com.duonghb.testbitrise.domain.model0

data class HorizontalModel(
    val client_id: Int,
    val device_token: String? = null,
    val user_id: Int? = null,
    val is_type: Int,
    val limit: Int,
    val offset: Int
)
