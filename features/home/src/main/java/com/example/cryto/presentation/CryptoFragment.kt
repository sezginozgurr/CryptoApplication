package com.example.cryto.presentation

import android.os.Bundle
import android.view.View
import com.example.base.BaseFragment
import com.example.home.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.example.base.globalExt.launchRepeatWithViewLifecycle
import com.example.base.globalExt.viewBinding
import com.example.cryto.presentation.adapter.CryptoListAdapter
import com.example.cryto.presentation.adapter.FavoritesAdapter
import com.example.home.databinding.FragmentCryptoBinding

@AndroidEntryPoint
class CryptoFragment : BaseFragment<CryptoViewModel>(R.layout.fragment_crypto) {

    override val viewModel: CryptoViewModel by viewModels<CryptoViewModel>()

    private val binding by viewBinding(FragmentCryptoBinding::bind)

    private val adapter by lazy {
        CryptoListAdapter({
            //item Click

        }, { pairName, last, dailyPercent ->
            viewModel.addFavorites(pairName, last, dailyPercent)
        })
    }

    private val favoriteAdapter by lazy { FavoritesAdapter { } }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        viewModel.getCrypto()
        viewModel.getAllFavorites()
        launchRepeatWithViewLifecycle {
            viewModel.uiState.collect { state ->
                adapter.submitList(state.list)
                favoriteAdapter.submitList(state.favoritesList)
            }
        }
    }

    private fun initUi() {
        viewModel.getCrypto()
        binding.rvCryptoList.adapter = adapter
        binding.rvFavorites.adapter = favoriteAdapter
        binding.tvPairs.text = "Pairs"
        binding.tvFavorites.text = "Favorites"
    }


}