package com.red_velvet.marvel.ui.home

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.databinding.FragmentHomeBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class ComicsFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutIdFragment = R.layout.fragment_home

    override val viewModel: ComicsViewModel by viewModels()

    override fun setUp() {

        binding.root.setOnClickListener {
            viewModel.getAllComics()
        }

        viewModel.comics.observe(this) {
            when (it) {
                is State.Failed -> {
                    binding.pBar.visibility = View.GONE
                    binding.textView.text = it.error
//                    Toast.makeText(requireActivity(), it.error, Toast.LENGTH_SHORT).show()
                    Snackbar.make(binding.root, it.error, Snackbar.LENGTH_SHORT).show()

                    Log.d("Mako", "setUp:  ${it.error}")
                }

                State.Loading -> {
                    binding.pBar.visibility = View.VISIBLE
                }

                is State.Success -> {
                    Log.d("Mako", "setUp:  ")
                    binding.pBar.visibility = View.GONE
                }
            }
        }


    }
}

