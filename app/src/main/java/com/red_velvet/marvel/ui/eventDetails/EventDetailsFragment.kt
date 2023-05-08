package com.red_velvet.marvel.ui.eventDetails


import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentEventDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment


class EventDetailsFragment : BaseFragment<FragmentEventDetailsBinding,EventDetailViewModel>() {

    override val layoutIdFragment = R.layout.fragment_event_details

    override val viewModel: EventDetailViewModel  by viewModels()

    private val args: EventDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val eventId = args.eventId
        viewModel.getEvent(eventId)
        viewModel.getCharactersEventId(eventId)
        viewModel.getCreatorsEventId(eventId)
    }

    override fun setUp() {
        val charactersAdapter = CharactersAdapter(mutableListOf(), viewModel)
        val creatorsAdapter = CreatorsAdapter(mutableListOf(), viewModel)

        binding.recyclerViewEventChars.adapter = charactersAdapter
        binding.recyclerViewEventCreators.adapter = creatorsAdapter

        navigateToCharacterDetails()
    }

    private fun navigateToCharacterDetails() {
        viewModel.navigationToCharacterDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    EventDetailsFragmentDirections.actionEventDetailsFragmentToCharacterFragment(it)
                findNavController().navigate(directions)
            }
        }
    }
}