package com.red_velvet.marvel.ui.serieDetails


import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentSerieDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment


class SerieDetails : BaseFragment<FragmentSerieDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_serie_details

    override val viewModel: SerieDetailsViewModel by viewModels()
    override fun setUp() {

    }
}