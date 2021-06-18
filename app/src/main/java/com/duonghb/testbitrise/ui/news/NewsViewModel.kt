package com.duonghb.testbitrise.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duonghb.testbitrise.constant.Constant
import com.duonghb.testbitrise.domain.model0.HorizontalModel
import com.duonghb.testbitrise.domain.usecase.GetNewsListHorizontalUseCase
import com.duonghb.testbitrise.domain.usecase.GetNewsListVerticalUseCase
import com.duonghb.testbitrise.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsListHorizontalUseCase: GetNewsListHorizontalUseCase,
    private val getNewsListVerticalUseCase: GetNewsListVerticalUseCase
) : BaseViewModel() {

    val getHorizontalCompleted: LiveData<List<HorizontalModel>> get() = _getHorizontalCompleted
    private val _getHorizontalCompleted = MutableLiveData<List<HorizontalModel>>()

    val getVerticalCompleted: LiveData<List<HorizontalModel>> get() = _getVerticalCompleted
    private val _getVerticalCompleted = MutableLiveData<List<HorizontalModel>>()
}
