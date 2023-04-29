package com.red_velvet.marvel.ui.home

import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentHomeBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.ui.base.BaseViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_home
    override val viewModel: BaseViewModel
        get() = HomeViewModel()

}