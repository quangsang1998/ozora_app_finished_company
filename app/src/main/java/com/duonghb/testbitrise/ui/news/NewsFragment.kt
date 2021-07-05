package com.duonghb.testbitrise.ui.news

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.NewsFragmentBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.news_fragment) {

    private val viewModel: NewsViewModel by viewModels()

    @Inject
    lateinit var adapterHorizontal: GroupAdapter<GroupieViewHolder>

    @Inject
    lateinit var adapterVertical: GroupAdapter<GroupieViewHolder>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = NewsFragmentBinding.bind(view)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val dividerItemDecoration =
            DividerItemDecorator(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
        binding.recyclerViewVertical.addItemDecoration(dividerItemDecoration)

        binding.recyclerViewHorizontal.adapter = adapterHorizontal
        binding.recyclerViewVertical.adapter = adapterVertical

        viewModel.getVerticalCompleted.observe(viewLifecycleOwner, ::setVerticalItems)
        viewModel.onEventVertical.observe(viewLifecycleOwner, ::handleEvent)

        viewModel.getHorizontalCompleted.observe(viewLifecycleOwner, ::setHorizontalItems)
        viewModel.onEventHorizontal.observe(viewLifecycleOwner, ::handleEvent)

        viewModel.getMoreVerticalCompleted.observe(viewLifecycleOwner, ::setMoreVerticalItems)
    }

    private fun setVerticalItems(news: List<VerticalListItemViewModel>) {
        adapterVertical.updateAsync(news.map { VerticalListItem(it) })
    }

    private fun setHorizontalItems(news: List<HorizontalListItemViewModel>) {
        adapterHorizontal.updateAsync(news.map { HorizontalListItem(it) })
    }

    private fun setMoreVerticalItems(news: List<VerticalListItemViewModel>) {
        adapterVertical.addAll(news.map { VerticalListItem(it) })
    }

    private fun handleEvent(event: NewsViewModel.Event) {
        when (event) {
            is NewsViewModel.Event.ClickedVerticalItem -> {
            }
            is NewsViewModel.Event.ClickedHorizontalItem -> {
            }
        }
    }
}
