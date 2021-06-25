package com.duonghb.testbitrise.ui.news

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.duonghb.testbitrise.constant.Constant
import com.duonghb.testbitrise.domain.model.ListItemHorizontal
import com.duonghb.testbitrise.domain.model.ListItemVertical
import com.duonghb.testbitrise.domain.usecase.GetNewsListHorizontalUseCase
import com.duonghb.testbitrise.domain.usecase.GetNewsListVerticalUseCase
import com.duonghb.testbitrise.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsListHorizontalUseCase: GetNewsListHorizontalUseCase,
    private val getNewsListVerticalUseCase: GetNewsListVerticalUseCase
) : BaseViewModel(), VerticalListItemViewModel.Listener, HorizontalListItemViewModel.Listener {

    val onEventVertical: LiveData<Event> get() = _onEventVertical
    private val _onEventVertical = MutableLiveData<Event>()

    val onEventHorizontal: LiveData<Event> get() = _onEventHorizontal
    private val _onEventHorizontal = MutableLiveData<Event>()

    val getHorizontalCompleted: LiveData<List<HorizontalListItemViewModel>?> get() = _getHorizontalCompleted
    private val _getHorizontalCompleted = MutableLiveData<List<HorizontalListItemViewModel>?>()

    val getVerticalCompleted: LiveData<List<VerticalListItemViewModel>?> get() = _getVerticalCompleted
    private val _getVerticalCompleted = MutableLiveData<List<VerticalListItemViewModel>?>()

    val swipeRefreshing: LiveData<Boolean> get() = _swipeRefreshing
    private val _swipeRefreshing = MutableLiveData<Boolean>()

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.map {
        if (it) View.VISIBLE else View.GONE
    }

    fun swipeRefreshingData() {
        _swipeRefreshing.postValue(true)
        getHorizontal()
        getVertical()
    }

    init {
        _loading.postValue(true)
        getHorizontal()
        getVertical()
    }

    private fun getVertical() {
        viewModelScope.launch {
            val getListVertical = getNewsListVerticalUseCase.invoke(
                Constant.CLIENT_ID,
                null,
                null,
                Constant.TYPE_VERTICAL,
                Constant.LIMIT_VERTICAL,
                Constant.OFFSET
            )
            try {
                val listVertical = getListVertical
                    .data?.list?.map {
                        VerticalListItemViewModel(it, this@NewsViewModel)
                    }
                _getVerticalCompleted.postValue(listVertical)
                _swipeRefreshing.postValue(false)
                _loading.postValue(false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getHorizontal() {
        viewModelScope.launch {
            val getListHorizontal = getNewsListHorizontalUseCase.invoke(
                Constant.CLIENT_ID,
                null,
                null,
                Constant.TYPE_HORIZONTAL,
                Constant.LIMIT_HORIZONTAL,
                Constant.OFFSET
            )
            try {
                val listHorizontal = getListHorizontal
                    .data?.list?.map {
                        HorizontalListItemViewModel(it, this@NewsViewModel)
                    }
                _getHorizontalCompleted.postValue(listHorizontal)
                _swipeRefreshing.postValue(false)
                _loading.postValue(false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onItemVerticalClick(listItemVertical: ListItemVertical?) {
        _onEventVertical.value = Event.ClickedVerticalItem(listItemVertical)
    }

    override fun onItemHorizontalClick(listItemHorizontal: ListItemHorizontal?) {
        _onEventHorizontal.value = Event.ClickedHorizontalItem(listItemHorizontal)
    }

    sealed class Event {
        class ClickedVerticalItem(val listItemVertical: ListItemVertical?) : Event()
        class ClickedHorizontalItem(val listItemHorizontal: ListItemHorizontal?) : Event()
    }
}
