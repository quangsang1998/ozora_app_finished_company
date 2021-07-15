package com.duonghb.testbitrise.ui.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.duonghb.testbitrise.constant.Constant
import com.duonghb.testbitrise.domain.model.ListItemLargeCategory
import com.duonghb.testbitrise.domain.usecase.GetListLargeCategoriesUseCase
import com.duonghb.testbitrise.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val getListLargeCategoriesUseCase: GetListLargeCategoriesUseCase
) : BaseViewModel() {

    val getListLargeCategory: LiveData<List<ListItemLargeCategory>>
        get() = _getListLargeCategory
    private val _getListLargeCategory =
        MutableLiveData<List<ListItemLargeCategory>>()

    init {
        getAllTagCategory()
    }

    fun getAllTagCategory() {
        viewModelScope.launch {
            getListLargeCategoriesUseCase.invoke(
                client_id = Constant.CLIENT_ID_LARGE_CATEGORY
            )
                .catch {
                }
                .collect {
                    _getListLargeCategory.postValue(requireNotNull(it))
                }
        }
    }
}
