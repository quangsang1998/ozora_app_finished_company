package com.duonghb.testbitrise.ui.news

import android.view.View
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.HorizontalItemBinding
import com.duonghb.testbitrise.domain.model.ListItemHorizontal
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

data class HorizontalListItemViewModel(
    val listItemHorizontal: ListItemHorizontal?,
    val listener: Listener
) {
    interface Listener {
        fun onItemHorizontalClick(listItemHorizontal: ListItemHorizontal?)
    }

    fun onItemHorizontalClick() {
        listener.onItemHorizontalClick(listItemHorizontal)
    }
}

class HorizontalListItem(
    private val viewModel: HorizontalListItemViewModel
) : BindableItem<HorizontalItemBinding>() {

    override fun getLayout(): Int = R.layout.horizontal_item

    override fun bind(viewBinding: HorizontalItemBinding, position: Int) {
        val binding: HorizontalItemBinding = viewBinding
        binding.viewModelHorizontal = viewModel
        viewBinding.executePendingBindings()
    }

    override fun initializeViewBinding(view: View): HorizontalItemBinding {
        return HorizontalItemBinding.bind(view)
    }

    override fun isSameAs(other: Item<*>): Boolean =
        (other as HorizontalListItem).viewModel.listItemHorizontal?.id ==
            viewModel.listItemHorizontal?.id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as HorizontalListItem).viewModel.listItemHorizontal == viewModel.listItemHorizontal
}
