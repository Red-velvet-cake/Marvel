package com.red_velvet.marvel.ui.eventDetails

import android.opengl.Visibility
import android.view.View
import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentEventDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.data.util.State


class EventDetailsFragment:BaseFragment<FragmentEventDetailsBinding>() {

    override val layoutIdFragment=R.layout.fragment_event_details
    override val viewModel:EventDetailVewModel by viewModels()

    override fun setUp() {
        viewModel.getEvent()
        viewModel.getCharactersEventId()
        viewModel.getCreatorsEventId()
        viewModel.event.observe(this){
            when(it){
                is State.Failed -> {
                    binding.pBar.visibility= View.GONE
                }
                is State.Success -> {
                    binding.pBar.visibility= View.GONE
                }
                is State.Loading -> {
                    binding.pBar.visibility= View.VISIBLE
                }
            }
        }
        viewModel.creators.observe(this){
            when(it){
                is State.Failed -> {
                    binding.pBar.visibility= View.GONE
                }
                is State.Success -> {
                    binding.pBar.visibility= View.GONE
                }
                is State.Loading -> {
                    binding.pBar.visibility= View.VISIBLE
                }
            }
        }
        viewModel.characters.observe(this){
            when(it){
                is State.Failed -> {
                    binding.pBar.visibility= View.GONE
                }
                is State.Success -> {
                    binding.pBar.visibility= View.GONE
                }
                is State.Loading -> {
                    binding.pBar.visibility= View.VISIBLE
                }
            }
        }
    }
}