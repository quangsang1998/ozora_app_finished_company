package com.duonghb.testbitrise.ui.news

import android.view.View
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.VerticalItemBinding
import com.duonghb.testbitrise.domain.model.ListItemVertical
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

data class VerticalListItemViewModel(
    val listItemVertical: ListItemVertical,
    val listener: Listener
) {
    interface Listener {
        fun onItemVerticalClick(listItemVertical: ListItemVertical)
    }

    fun onItemVerticalClick() {
        listener.onItemVerticalClick(listItemVertical)
    }
}

class VerticalListItem(
    private val viewModel: VerticalListItemViewModel
) : BindableItem<VerticalItemBinding>() {
    override fun getLayout(): Int = R.layout.vertical_item

    override fun bind(viewBinding: VerticalItemBinding, position: Int) {
        val binding: VerticalItemBinding = viewBinding
        binding.viewModelVertical = viewModel
        viewBinding.executePendingBindings()
    }

    override fun initializeViewBinding(view: View): VerticalItemBinding {
        return VerticalItemBinding.bind(view)
    }

    override fun isSameAs(other: Item<*>): Boolean =
        (other as VerticalListItem).viewModel.listItemVertical.id ==
            viewModel.listItemVertical.id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as VerticalListItem).viewModel.listItemVertical == viewModel.listItemVertical
}
