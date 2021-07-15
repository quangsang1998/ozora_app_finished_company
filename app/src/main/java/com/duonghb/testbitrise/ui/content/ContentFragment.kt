package com.duonghb.testbitrise.ui.content

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.ContentFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.content_fragment.*

@AndroidEntryPoint
class ContentFragment : Fragment(R.layout.content_fragment) {

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private val contentViewModel: ContentViewModel by viewModels()

    private val tabViewModel: TabViewModel by viewModels()

    private var binding: ContentFragmentBinding? = null
    private val safeActivity by lazy {
        requireActivity() as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ContentFragmentBinding.bind(view)
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.viewModel = tabViewModel

        initUi()
    }

    private fun initUi() {

        safeActivity.supportActionBar?.elevation = R.dimen.elevation.toFloat()

        contentViewModel.getListLargeCategory.observe(
            viewLifecycleOwner,
            { categories ->
                viewPagerAdapter = ViewPagerAdapter(this, categories.map { it.id })
                binding?.viewPager?.adapter = viewPagerAdapter

                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = (categories[position].name)
                }.attach()
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
