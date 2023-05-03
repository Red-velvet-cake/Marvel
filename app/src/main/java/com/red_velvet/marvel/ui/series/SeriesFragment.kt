package com.red_velvet.marvel.ui.series

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.databinding.FragmentSeriesBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class SeriesFragment : BaseFragment<FragmentSeriesBinding>() {

    override val layoutIdFragment = R.layout.fragment_series

    override val viewModel: SeriesViewModel by viewModels()

    override fun setUp() {
        viewModel.series.observe(this) {
            when (it) {
                is State.Failed -> {
                }

                State.Loading -> {
                }

                is State.Success -> {
                }
            }
        }
    }



}