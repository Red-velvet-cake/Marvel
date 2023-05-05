package com.red_velvet.marvel.ui.eventDetails


import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentEventDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.ui.eventDetails.adapter.CharactersAdapter
import com.red_velvet.marvel.ui.eventDetails.adapter.CreatorsAdapter


class EventDetailsFragment:BaseFragment<FragmentEventDetailsBinding>() {

    override val layoutIdFragment=R.layout.fragment_event_details
    override val viewModel:EventDetailVewModel by viewModels()

    override fun setUp() {
        viewModel.getEvent()
        viewModel.getCharactersEventId()
        viewModel.getCreatorsEventId()
        val charactersAdapter = CharactersAdapter(mutableListOf())
        val creatorsAdapter = CreatorsAdapter(mutableListOf())
        binding.recyclerViewEventChars.adapter = charactersAdapter
        binding.recyclerViewEventCreators.adapter = creatorsAdapter
    }
}