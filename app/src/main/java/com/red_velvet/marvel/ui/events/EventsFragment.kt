package com.red_velvet.marvel.ui.events


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentEventsBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : BaseFragment<FragmentEventsBinding, EventsViewModel>() {

    override val layoutIdFragment: Int = R.layout.fragment_events

    override val viewModel: EventsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = EventsAdapter(emptyList(), viewModel)
        binding.recyclerViewEvents.adapter = adapter
        binding.textViewError.setOnClickListener { viewModel.getAllEvents() }
        initNavigateToEventDetails()
    }

    private fun initNavigateToEventDetails() {
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