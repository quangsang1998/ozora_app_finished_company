package com.duonghb.testbitrise.ui.news

import android.view.View
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.VerticalItemBinding
import com.duonghb.testbitrise.domain.model1.VerticalModel
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

class VerticalListItemViewModel(
    val verticalItem: VerticalModel,
    val listener: Listener
) {
    interface Listener {
        fun onItemVerticalClick(verticalItem: VerticalModel)
    }

    fun onItemVerticalClick() {
        listener.onItemVerticalClick(verticalItem)
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
        (other as VerticalListItem).viewModel.verticalItem.client_id ==
            viewModel.verticalItem.client_id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as VerticalListItem).viewModel.verticalItem == viewModel.verticalItem
}
