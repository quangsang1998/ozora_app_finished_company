package com.duonghb.testbitrise.domain.result

import com.duonghb.testbitrise.domain.model0.HorizontalModel
import timber.log.Timber

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val e: Timber) : Result<Nothing>()
}
