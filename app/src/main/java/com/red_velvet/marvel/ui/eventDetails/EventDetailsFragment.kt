package com.red_velvet.marvel.ui.eventDetails


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentEventDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment


class EventDetailsFragment : BaseFragment<FragmentEventDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_event_details

    override val viewModel: EventDetailViewModel by viewModels()

    private val args: EventDetailsFragmentArgs by navArgs()
    override fun setUp() {
        val eventId = args.eventId

        viewModel.getEvent(eventId)
        viewModel.getCharactersEventId(eventId)
        viewModel.getCreatorsEventId(eventId)

        val charactersAdapter = CharactersAdapter(mutableListOf(), viewModel)
        val creatorsAdapter = CreatorsAdapter(mutableListOf(), viewModel)

        binding.recyclerViewEventChars.adapter = charactersAdapter
        binding.recyclerViewEventCreators.adapter = creatorsAdapter
    }
}