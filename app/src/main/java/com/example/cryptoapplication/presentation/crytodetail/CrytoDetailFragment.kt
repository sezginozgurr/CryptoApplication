package com.example.cryptoapplication.presentation.crytodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cryptoapplication.common.collect
import com.example.cryptoapplication.databinding.FragmentCrytoDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CrytoDetailFragment : Fragment() {

    private var _binding: FragmentCrytoDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CrytoDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCrytoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {}

        collectState()
    }

    private fun collectState() {
        with(binding) {
            viewModel.uiState.collect(viewLifecycleOwner) { state ->

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}