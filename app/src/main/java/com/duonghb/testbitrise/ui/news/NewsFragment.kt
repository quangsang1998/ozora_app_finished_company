package com.duonghb.testbitrise.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.NewsFragmentBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class NewsFragment: Fragment(R.layout.news_fragment) {

  @Inject
  lateinit var adapter: GroupAdapter<GroupieViewHolder>

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val binding = NewsFragmentBinding.bind(view)

    binding.lifecycleOwner = viewLifecycleOwner

    binding.recyclerViewHorizontal.adapter = adapter
    binding.recyclerViewVertical.adapter = adapter
  }
}