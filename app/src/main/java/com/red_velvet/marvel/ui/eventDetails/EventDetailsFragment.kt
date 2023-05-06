package com.red_velvet.marvel.ui.eventDetails


import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentEventDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment


class EventDetailsFragment : BaseFragment<FragmentEventDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_event_details
    override val viewModel: EventDetailVewModel by viewModels()
    private val eventId = 116
    override fun setUp() {
        viewModel.getEvent(eventId)
        viewModel.getCharactersEventId(eventId)
        viewModel.getCreatorsEventId(eventId)
        val charactersAdapter = CharactersAdapter(mutableListOf())
        val creatorsAdapter = CreatorsAdapter(mutableListOf())
        binding.recyclerViewEventChars.adapter = charactersAdapter
        binding.recyclerViewEventCreators.adapter = creatorsAdapter
    }
}