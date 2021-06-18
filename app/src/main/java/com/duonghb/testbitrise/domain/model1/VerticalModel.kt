package com.duonghb.testbitrise.domain.model1

class VerticalModel(
    val client_id: Int,
    val device_token: String? = null,
    val user_id: Int? = null,
    val is_type: Int,
    val limit: Int,
    val offset: Int
)
