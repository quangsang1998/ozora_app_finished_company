package com.duonghb.testbitrise.ext

import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog

fun Fragment.showStateDialog(
    title: Int? = null,
    message: String?,
    positiveName: Int
) {
    val context = context ?: return
    MaterialDialog(context).show {
        title(title)
        message(text = message)
        positiveButton(positiveName)
    }
}
