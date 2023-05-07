package com.red_velvet.marvel.ui.events


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentEventsBinding
import com.red_velvet.marvel.ui.base.BaseFragment


class EventsFragment() : BaseFragment<FragmentEventsBinding>() {

    override val layoutIdFragment: Int = R.layout.fragment_events

    override val viewModel: EventsViewModel by viewModels()

    override fun setUp() {
        val adapter = EventsAdapter(emptyList(), viewModel)
        binding.recyclerViewEvents.adapter = adapter
        navigateToEventDetails()
    }

    private fun navigateToEventDetails() {
        viewModel.navigationToEventDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    EventsFragmentDirections.actionEventsFragmentToEventDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.searchQuery.removeObservers(this)
    }

}