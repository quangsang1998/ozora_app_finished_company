package com.duonghb.testbitrise.ui.content

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.ContentFragmentBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class ContentFragment: Fragment(R.layout.content_fragment) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val binding = ContentFragmentBinding.bind(view)

  }
}