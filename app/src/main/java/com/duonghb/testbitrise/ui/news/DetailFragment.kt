package com.duonghb.testbitrise.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = DetailFragmentBinding.bind(view)

        binding.lifecycleOwner = viewLifecycleOwner
    }
}
