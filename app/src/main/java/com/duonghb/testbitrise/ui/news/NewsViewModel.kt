package com.duonghb.testbitrise.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duonghb.testbitrise.constant.Constant
import com.duonghb.testbitrise.domain.model0.HorizontalModel
import com.duonghb.testbitrise.domain.model1.VerticalModel
import com.duonghb.testbitrise.domain.usecase.GetNewsListHorizontalUseCase
import com.duonghb.testbitrise.domain.usecase.GetNewsListVerticalUseCase
import com.duonghb.testbitrise.ui.common.BaseViewModel
import kotlinx.coroutines.*
import timber.log.Timber

class NewsViewModel(
    private val getNewsListHorizontalUseCase: GetNewsListHorizontalUseCase,
    private val getNewsListVerticalUseCase: GetNewsListVerticalUseCase
) : BaseViewModel() {

    val getHorizontalCompleted: LiveData<List<HorizontalModel>> get() = _getHorizontalCompleted
    private val _getHorizontalCompleted = MutableLiveData<List<HorizontalModel>>()

    val getVerticalCompleted: LiveData<List<VerticalModel>> get() = _getVerticalCompleted
    private val _getVerticalCompleted = MutableLiveData<List<VerticalModel>>()

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    private fun getVertical() {
        coroutineScope.launch {
            val getListVertical = getNewsListVerticalUseCase.invoke(Constant.API_KEY_0)
            try {
                val listVertical = getListVertical.await()
                _getVerticalCompleted.postValue(listVertical)
            } catch (e: Exception) {
                Timber.e("${e.message}")
            }
        }
    }

    override fun onCleared() {
      super.onCleared()
      viewModelJob.cancel()
    }
}
