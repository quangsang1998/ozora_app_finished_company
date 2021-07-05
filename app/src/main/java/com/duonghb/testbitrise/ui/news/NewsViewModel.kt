package com.duonghb.testbitrise.ui.news

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.constant.Constant
import com.duonghb.testbitrise.domain.model.ListItemHorizontal
import com.duonghb.testbitrise.domain.model.ListItemVertical
import com.duonghb.testbitrise.domain.usecase.GetNewsListHorizontalUseCase
import com.duonghb.testbitrise.domain.usecase.GetNewsListVerticalUseCase
import com.duonghb.testbitrise.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.zip
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

    val getHorizontalCompleted: LiveData<List<HorizontalListItemViewModel>>
        get() = _getHorizontalCompleted
    private val _getHorizontalCompleted = MutableLiveData<List<HorizontalListItemViewModel>>()

    val getVerticalCompleted: LiveData<List<VerticalListItemViewModel>>
        get() = _getVerticalCompleted
    private val _getVerticalCompleted = MutableLiveData<List<VerticalListItemViewModel>>()

    val getMoreVerticalCompleted: LiveData<List<VerticalListItemViewModel>>
        get() = _getMoreVerticalCompleted
    private val _getMoreVerticalCompleted = MutableLiveData<List<VerticalListItemViewModel>>()

    val swipeRefreshing: LiveData<Boolean> get() = _swipeRefreshing
    private val _swipeRefreshing = MutableLiveData<Boolean>()

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.map {
        if (it) View.VISIBLE else View.GONE
    }

    private val _buttonLoad = MutableLiveData(StateLoadMore.START)

    val buttonLoad = _buttonLoad.map {
        when (it) {
            StateLoadMore.START, StateLoadMore.DONE, StateLoadMore.ERROR -> View.VISIBLE
            StateLoadMore.LOADING, StateLoadMore.END -> View.GONE
        }
    }

    val loadingMore = _buttonLoad.map {
        when (it) {
            StateLoadMore.LOADING -> View.VISIBLE
            else -> View.GONE
        }
    }

    val loadMoreText = _buttonLoad.map {
        when (it) {
            StateLoadMore.ERROR -> R.string.load_more_error
            else -> R.string.loadmore
        }
    }

    private var offSet: Int = 0

    fun swipeRefreshingData() {
        offSet = 0
        _swipeRefreshing.postValue(true)
        getALl()
    }

    init {
        _buttonLoad.postValue(StateLoadMore.END)
        _loading.postValue(true)
        getALl()
    }

    fun getALl() {
        viewModelScope.launch {
            val getListVertical = getNewsListVerticalUseCase.invoke(
                Constant.CLIENT_ID,
                null,
                null,
                Constant.TYPE_VERTICAL,
                Constant.LIMIT_VERTICAL,
                Constant.OFFSET
            )

            val getListHorizontal = getNewsListHorizontalUseCase.invoke(
                Constant.CLIENT_ID,
                null,
                null,
                Constant.TYPE_HORIZONTAL,
                Constant.LIMIT_HORIZONTAL,
                Constant.OFFSET
            )
            getListHorizontal.zip(getListVertical) { a, b ->

                val h = a.data.list.map {
                    HorizontalListItemViewModel(it, this@NewsViewModel)
                }

                val v = b.data.list.map {
                    VerticalListItemViewModel(it, this@NewsViewModel)
                }

                Pair(h, v)
            }
                .catch {
                    _loading.postValue(false)
                }
                .collect {
                    offSet += Constant.LIMIT_VERTICAL
                    _getHorizontalCompleted.postValue(requireNotNull(it.first))
                    _getVerticalCompleted.postValue(requireNotNull(it.second))
                    _swipeRefreshing.postValue(false)
                    _loading.postValue(false)
                    _buttonLoad.postValue(StateLoadMore.DONE)
                }
        }
    }

    fun loadMore() {
        _buttonLoad.postValue(StateLoadMore.LOADING)
        viewModelScope.launch {
            val getListVertical = getNewsListVerticalUseCase.invoke(
                Constant.CLIENT_ID,
                null,
                null,
                Constant.TYPE_VERTICAL,
                Constant.LIMIT_VERTICAL,
                offSet
            )

            getListVertical
                .catch {
                    _buttonLoad.postValue(StateLoadMore.ERROR)
                }
                .collect {
                    offSet += Constant.LIMIT_VERTICAL
                    val v = it.data.list.map {
                        VerticalListItemViewModel(it, this@NewsViewModel)
                    }
                    _getMoreVerticalCompleted.postValue(v)
                    _buttonLoad.postValue(StateLoadMore.DONE)
                    if (it.data.list.size < Constant.LIMIT_VERTICAL) {
                        _buttonLoad.postValue(StateLoadMore.END)
                    }
                }
        }
    }

    override fun onItemVerticalClick(listItemVertical: ListItemVertical) {
        _onEventVertical.value = Event.ClickedVerticalItem(listItemVertical)
    }

    override fun onItemHorizontalClick(listItemHorizontal: ListItemHorizontal) {
        _onEventHorizontal.value = Event.ClickedHorizontalItem(listItemHorizontal)
    }

    sealed class Event {
        class ClickedVerticalItem(val listItemVertical: ListItemVertical) : Event()
        class ClickedHorizontalItem(val listItemHorizontal: ListItemHorizontal) : Event()
        class ClickedVerticalItemLoadMore(val listItemVertical: ListItemVertical) : Event()
    }

    enum class StateLoadMore {
        START,
        LOADING,
        DONE,
        ERROR,
        END;
    }
}
