package com.duonghb.testbitrise.ui.content

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.constant.Constant
import com.duonghb.testbitrise.databinding.TabItemBinding
import com.duonghb.testbitrise.domain.model.ListItemSubCategory
import com.duonghb.testbitrise.domain.model.ListItemSubContent
import com.duonghb.testbitrise.domain.model.TabContent
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

data class SubCategoryListItemViewModel(
    val tabContent: TabContent,
    val listener: Listener,
    val contentlistener: SubContentListItemViewModel.Listener,
    val firstContentListener: FirstSubContentViewModel.Listener
) {
    private var isLoading: Boolean = true

    interface Listener {
        fun onItemSubCategoryClick(listItemSubCategory: ListItemSubCategory?)
    }

    fun onItemSubCategoryClick() {
        listener.onItemSubCategoryClick(tabContent.subCategory)
    }

    fun getState(): LoadState {
        when (isLoading) {
            true -> return LoadState.LOADING
            false -> return LoadState.DONE
        }
    }

    val name get() = tabContent.subCategory?.name
}

interface Callback {
    fun addItem(position: Int, list: List<ListItemSubContent>)
}

class SubCategoryListItem(
    private val viewModel: SubCategoryListItemViewModel,
    private val tabViewModel: TabViewModel
) : BindableItem<TabItemBinding>(), Callback {

    private var offset: Int = 0

    val adapter = GroupAdapter<GroupieViewHolder>()

    override fun getLayout(): Int = R.layout.tab_item

    override fun bind(viewBinding: TabItemBinding, position: Int) {
        val binding: TabItemBinding = viewBinding
        binding.viewModel = viewModel
        viewBinding.executePendingBindings()

        binding.recyclerView2.adapter = adapter

        renderItem(position)

        binding.recyclerView2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager: LinearLayoutManager = recyclerView.layoutManager
                    as LinearLayoutManager

                if (viewModel.getState() == LoadState.LOADING &&
                    linearLayoutManager.findLastCompletelyVisibleItemPosition() ==
                    viewModel.tabContent.contents.size.minus(1)
                ) {
                    offset += Constant.LIMIT_SUB_CATEGORY
                    tabViewModel.loadMore(
                        viewModel.tabContent.subCategory,
                        offset, position,
                        this@SubCategoryListItem
                    )
                }
            }
        })
    }

    override fun initializeViewBinding(view: View): TabItemBinding {
        return TabItemBinding.bind(view)
    }

    override fun isSameAs(other: Item<*>): Boolean =
        (other as SubCategoryListItem).viewModel.tabContent.subCategory?.id ==
            viewModel.tabContent.subCategory?.id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as SubCategoryListItem).viewModel.tabContent.contents ==
            viewModel.tabContent.contents

    override fun addItem(position: Int, list: List<ListItemSubContent>) {

        if (list.size < Constant.LIMIT_SUB_CATEGORY) {
            LoadState.DONE
        }

        when (position) {
            0 -> list.map {
                FirstSubContentListItem(
                    FirstSubContentViewModel(
                        it,
                        viewModel.firstContentListener
                    )
                )
            }.let { adapter.addAll(it) }

            else -> list.map {
                if (it.thumbnailType == 1) {
                    SubContentRectangleListItem(
                        SubContentListItemViewModel(
                            it,
                            viewModel.contentlistener
                        )
                    )
                } else {
                    SubContentListItem(SubContentListItemViewModel(it, viewModel.contentlistener))
                }
            }.let { adapter.addAll(it) }
        }
    }

    private fun renderItem(position: Int) {
        when (position) {
            0 ->
                viewModel.tabContent.contents.map {
                    FirstSubContentListItem(
                        FirstSubContentViewModel(
                            it,
                            viewModel.firstContentListener
                        )
                    )
                }.let {
                    adapter.updateAsync(it)
                }

            else ->
                viewModel.tabContent.contents.map {
                    if (it.thumbnailType == 1) {
                        SubContentRectangleListItem(
                            SubContentListItemViewModel(
                                it,
                                viewModel.contentlistener
                            )
                        )
                    } else {
                        SubContentListItem(
                            SubContentListItemViewModel(
                                it,
                                viewModel.contentlistener
                            )
                        )
                    }
                }.let {
                    adapter.updateAsync(it)
                }
        }
    }
}
