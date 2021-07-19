package com.duonghb.testbitrise.ui.content

import android.view.View
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.SubItemBinding
import com.duonghb.testbitrise.databinding.SubRectangleItemBinding
import com.duonghb.testbitrise.domain.model.ListItemSubContent
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

data class SubContentListItemViewModel(
    val content: ListItemSubContent,
    val listener: Listener
) {

    interface Listener {
        fun onItemSubContentClick(listItemSubContent: ListItemSubContent)
    }

    val image get() = content.thumbnail
    val title get() = content.title
}

class SubContentListItem(
    private val viewModel: SubContentListItemViewModel
) : BindableItem<SubItemBinding>() {

    override fun getLayout(): Int = R.layout.sub_item

    override fun bind(viewBinding: SubItemBinding, position: Int) {
        val binding: SubItemBinding = viewBinding
        binding.viewModel = viewModel
        viewBinding.executePendingBindings()
    }

    override fun initializeViewBinding(view: View): SubItemBinding {
        return SubItemBinding.bind(view)
    }

    override fun isSameAs(other: Item<*>): Boolean =
        (other as SubContentListItem).viewModel.content.id ==
            viewModel.content.id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as SubContentListItem).viewModel.content ==
            viewModel.content
}

class SubContentRectangleListItem(
    private val viewModel: SubContentListItemViewModel
) : BindableItem<SubRectangleItemBinding>() {

    override fun getLayout(): Int = R.layout.sub_rectangle_item

    override fun bind(viewBinding: SubRectangleItemBinding, position: Int) {
        val binding: SubRectangleItemBinding = viewBinding
        binding.viewModel = viewModel
        viewBinding.executePendingBindings()
    }

    override fun initializeViewBinding(view: View): SubRectangleItemBinding {
        return SubRectangleItemBinding.bind(view)
    }

    override fun isSameAs(other: Item<*>): Boolean =
        (other as SubContentRectangleListItem).viewModel.content.id ==
            viewModel.content.id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as SubContentRectangleListItem).viewModel.content ==
            viewModel.content
}
