package com.duonghb.testbitrise.ui.content

import com.duonghb.testbitrise.R
import retrofit2.HttpException
import java.net.UnknownHostException

class ErrorHandle() {
    fun getErrorMessage(throwable: Throwable): String? {
        val message: String?
        return when (throwable) {
            is UnknownHostException ->
                R.string.network_notification.toString()

            is HttpException -> {
                message = throwable.response()?.errorBody()?.string()
                return message
            }

            else ->
                throwable.message
        }
    }
}
