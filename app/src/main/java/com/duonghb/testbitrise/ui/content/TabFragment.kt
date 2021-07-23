package com.duonghb.testbitrise.ui.content

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.TabFragmentBinding
import com.duonghb.testbitrise.ext.showStateDialog
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TabFragment : Fragment(R.layout.tab_fragment) {

    @Inject
    lateinit var groupAdapter: GroupAdapter<GroupieViewHolder>

    val viewModel: TabViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = TabFragmentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.recyclerView.adapter = groupAdapter

        arguments?.getInt(CATEGORY_ID)?.let {
            viewModel.init(it)
        }

        viewModel.onEventError.observe(viewLifecycleOwner, ::showError)

        viewModel.getListSubCategory.observe(viewLifecycleOwner, ::setSubCategories)
    }

    private fun setSubCategories(subCategories: List<SubCategoryListItemViewModel>) {
        groupAdapter.updateAsync(subCategories.map { SubCategoryListItem(it, viewModel) })
    }

    private fun showError(event: TabViewModel.Event) {
        when (event) {
            is TabViewModel.Event.Error ->
                this.showStateDialog(
                    title = R.string.error_notification,
                    message = event.message,
                    positiveName = R.string.positive_dialog,
                    negativeName = R.string.negative_dialog
                )
        }
    }

    companion object {
        const val CATEGORY_ID = "category_id"

        fun newInstant(categoryId: Int) = TabFragment().also {
            it.arguments = bundleOf(CATEGORY_ID to categoryId)
        }
    }
}
