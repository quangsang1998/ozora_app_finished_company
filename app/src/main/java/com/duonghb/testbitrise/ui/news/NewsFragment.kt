package com.duonghb.testbitrise.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.NewsFragmentBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.news_fragment.*
import timber.log.Timber
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

        val dividerItemDecoration = DividerItemDecoration(
            recyclerViewVertical.getContext(),
            LinearLayoutManager.VERTICAL
        )
        recyclerViewVertical.addItemDecoration(dividerItemDecoration)

        binding.recyclerViewHorizontal.adapter = adapterHorizontal
        binding.recyclerViewVertical.adapter = adapterVertical

        viewModel.getVerticalCompleted.observe(viewLifecycleOwner, ::setVerticalItems)
        viewModel.onEventVertical.observe(viewLifecycleOwner, ::handleEvent)

        viewModel.getHorizontalCompleted.observe(viewLifecycleOwner, ::setHorizontalItems)
        viewModel.onEventHorizontal.observe(viewLifecycleOwner, ::handleEvent)

//        recyclerViewVertical.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })
    }

    private fun setVerticalItems(news: List<VerticalListItemViewModel>?) {
        if (news != null) {
            adapterVertical.updateAsync(news.map { VerticalListItem(it) })
        } else {
            Timber.e("error")
        }
    }

    private fun setHorizontalItems(news: List<HorizontalListItemViewModel>?) {
        if (news != null) {
            adapterHorizontal.updateAsync(news.map { HorizontalListItem(it) })
        } else {
            Timber.e("error")
        }
    }

    private fun handleEvent(event: NewsViewModel.Event) {
        when (event) {
            is NewsViewModel.Event.ClickedVerticalItem -> {
                findNavController().navigate(NewsFragmentDirections.actionNavigationNewsToNavigationDetail())
            }
            is NewsViewModel.Event.ClickedHorizontalItem -> {
            }
        }
    }
}
