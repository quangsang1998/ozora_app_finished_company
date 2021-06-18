package com.duonghb.testbitrise.ui.news

import android.view.View
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.HorizontalItemBinding
import com.duonghb.testbitrise.domain.model0.HorizontalModel
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

class HorizontalListItemViewModel(
    val horizontalModel: HorizontalModel,
    val listener: Listener
) {
    interface Listener {
        fun onItemHorizontalClick(horizontalModel: HorizontalModel)
    }

    fun onItemHorizontalClick() {
        listener.onItemHorizontalClick(horizontalModel)
    }
}

class HorizontalListItem(
    private val viewModel: HorizontalListItemViewModel
) : BindableItem<HorizontalItemBinding>() {
    override fun getLayout(): Int = R.layout.vertical_item

    override fun bind(viewBinding: HorizontalItemBinding, position: Int) {
        val binding: HorizontalItemBinding = viewBinding
        binding.viewModelHorizontal = viewModel
        viewBinding.executePendingBindings()
    }

    override fun initializeViewBinding(view: View): HorizontalItemBinding {
        return HorizontalItemBinding.bind(view)
    }

    override fun isSameAs(other: Item<*>): Boolean =
        (other as HorizontalListItem).viewModel.horizontalModel.client_id ==
            viewModel.horizontalModel.client_id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as HorizontalListItem).viewModel.horizontalModel == viewModel.horizontalModel
}
