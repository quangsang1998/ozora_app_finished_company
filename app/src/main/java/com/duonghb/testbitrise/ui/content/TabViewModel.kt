package com.duonghb.testbitrise.ui.content

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.duonghb.testbitrise.constant.Constant
import com.duonghb.testbitrise.domain.model.ListItemSubCategory
import com.duonghb.testbitrise.domain.model.ListItemSubContent
import com.duonghb.testbitrise.domain.usecase.GetListSubCategoriesUseCase
import com.duonghb.testbitrise.domain.usecase.GetListSubContentUseCase
import com.duonghb.testbitrise.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabViewModel @Inject constructor(
    private val getListSubCategoriesUseCase: GetListSubCategoriesUseCase,
    private val getListSubContentUseCase: GetListSubContentUseCase
) : BaseViewModel(),
    SubCategoryListItemViewModel.Listener,
    SubContentListItemViewModel.Listener,
    FirstSubContentViewModel.Listener {

    val getListSubCategory: LiveData<List<SubCategoryListItemViewModel>>
        get() = _getListSubCategory
    private val _getListSubCategory = MutableLiveData<List<SubCategoryListItemViewModel>>()

    val onEventSubCategory: LiveData<Event> get() = _onEventSubCategory
    private val _onEventSubCategory = MutableLiveData<Event>()

    val onEventSubContent: LiveData<Event> get() = _onEventSubContent
    private val _onEventSubContent = MutableLiveData<Event>()

    val swipeRefreshing: LiveData<Boolean> get() = _swipeRefreshing
    private val _swipeRefreshing = MutableLiveData<Boolean>()

    private val _loadContents = MutableLiveData<Boolean>()
    val loadContents = _loadContents.map {
        if (it) View.VISIBLE else View.GONE
    }

    val onEventError: LiveData<Event> get() = _onEventError
    private val _onEventError = MutableLiveData<Event>()

    private var categoryId: Int = 0

    fun init(categoryId: Int) {
        _loadContents.postValue(true)
        getAll(categoryId)
    }

    fun swipeRefreshingData() {
        getAll(categoryId)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun getAll(categoryId: Int) {
        this.categoryId = categoryId
        viewModelScope.launch {
            getListSubCategoriesUseCase.invoke(
                client_id = Constant.CLIENT_ID,
                id = categoryId
            )
                .flatMapConcat { list ->
                    list.map {
                        getListSubContentUseCase.invoke(
                            client_id = Constant.CLIENT_ID,
                            category_id = categoryId,
                            subCategory = it,
                            limit = Constant.LIMIT_SUB_CATEGORY,
                            offset = Constant.OFFSET_SUB_CATEGORY
                        )
                    }.toMutableList().apply {
                        add(
                            0,
                            getListSubContentUseCase.invoke(
                                client_id = Constant.CLIENT_ID,
                                category_id = categoryId,
                                limit = Constant.LIMIT_SUB_CATEGORY,
                                offset = Constant.OFFSET_SUB_CATEGORY
                            )
                        )
                    }.let {
                        combine(it) { s -> s }
                    }
                }
                .map {
                    it.toList().map { item ->
                        SubCategoryListItemViewModel(
                            item,
                            this@TabViewModel,
                            this@TabViewModel,
                            this@TabViewModel
                        )
                    }
                }
                .catch {
                    _onEventError.value = Event.Error(
                        ErrorHandle().getErrorMessage(this.run { it })
                    )
                }
                .collect {
                    _getListSubCategory.postValue(it)
                    _loadContents.postValue(false)
                    _swipeRefreshing.postValue(false)
                }
        }
    }

    fun loadMore(
        subCategory: ListItemSubCategory?,
        offset: Int,
        position: Int,
        callBack: Callback
    ) {

        viewModelScope.launch {
            getListSubContentUseCase.invoke(
                client_id = Constant.CLIENT_ID,
                category_id = categoryId,
                subCategory = subCategory,
                limit = Constant.LIMIT_SUB_CATEGORY,
                offset = offset
            ).map {
                it.contents
            }
                .catch {
                    _onEventError.value = Event.Error(
                        ErrorHandle().getErrorMessage(this.run { it })
                    )
                }
                .collect {
                    callBack.addItem(position, it)
                }
        }
    }

    override fun onItemSubCategoryClick(listItemSubCategory: ListItemSubCategory?) {
    }

    override fun onItemSubContentClick(listItemSubContent: ListItemSubContent) {
    }

    override fun onItemFirstSubContentClick(listItemSubContent: ListItemSubContent) {
    }

    sealed class Event {
        class ClickItemSubCategory(val listItemSubCategory: ListItemSubCategory) : Event()
        class ClickItemSubContent(val listItemSubContent: ListItemSubContent) : Event()
        class Error(val message: String?) : Event()
    }
}
