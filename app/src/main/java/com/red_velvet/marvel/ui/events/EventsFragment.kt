package com.red_velvet.marvel.ui.events


import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentEventsBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.ui.base.BaseViewModel


class EventsFragment() : BaseFragment<FragmentEventsBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_events
    override val viewModel:EventsViewModel by viewModels()
    override fun setUp() {
        val adapter = EventsAdapter(emptyList(), viewModel)
        binding.recyclerViewEvents.adapter = adapter

        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.getAllEvents(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.getAllEvents()
            }
        })

    }


}