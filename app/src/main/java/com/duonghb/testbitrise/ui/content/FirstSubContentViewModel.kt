package com.duonghb.testbitrise.ui.content

import android.view.View
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.FirstItemBinding
import com.duonghb.testbitrise.domain.model.ListItemSubContent
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

class FirstSubContentViewModel(
    val content: ListItemSubContent,
    val listener: Listener
) {
    interface Listener {
        fun onItemFirstSubContentClick(listItemSubContent: ListItemSubContent)
    }

    val image get() = content.thumbnail
    val text get() = content.subtitle
}

class FirstSubContentListItem(
    private val viewModel: FirstSubContentViewModel
) : BindableItem<FirstItemBinding>() {

    override fun getLayout(): Int = R.layout.first_item

    override fun bind(viewBinding: FirstItemBinding, position: Int) {
        val binding: FirstItemBinding = viewBinding
        binding.viewModel = viewModel
    }

    override fun initializeViewBinding(view: View): FirstItemBinding {
        return FirstItemBinding.bind(view)
    }

    override fun isSameAs(other: Item<*>): Boolean =
        (other as FirstSubContentListItem).viewModel.content.id ==
            viewModel.content.id

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as FirstSubContentListItem).viewModel.content ==
            viewModel.content
}
