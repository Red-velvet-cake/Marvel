package com.red_velvet.marvel.ui.eventDetails

import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentEventDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class EventDetailsFragment:BaseFragment<FragmentEventDetailsBinding>() {
    override val layoutIdFragment=R.layout.fragment_event_details
    override val viewModel:EventDetailVewModel by viewModels()
    override fun setUp() {
        TODO("Not yet implemented")
    }
}