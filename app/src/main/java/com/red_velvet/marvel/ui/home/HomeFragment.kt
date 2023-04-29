package com.red_velvet.marvel.ui.home

import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentHomeBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutIdFragment = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModels()
}