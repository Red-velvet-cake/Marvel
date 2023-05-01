package com.red_velvet.marvel.ui.home

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.databinding.FragmentHomeBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class ComicsFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutIdFragment = R.layout.fragment_home

    override val viewModel: ComicsViewModel by viewModels()

    override fun setUp() {

        viewModel.comics.observe(this) {
            when (it) {
                is State.Failed -> {
                    binding.pBar.visibility = View.VISIBLE
                }

                State.Loading -> {
                }

                is State.Success -> {
                    Log.d("Mako", "setUp:  ")
                    binding.pBar.visibility = View.GONE
                }
            }
        }


    }
}

