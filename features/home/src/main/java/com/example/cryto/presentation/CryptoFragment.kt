package com.example.cryto.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.base.BaseFragment
import com.example.home.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.example.base.globalExt.launchRepeatWithViewLifecycle
import com.example.base.globalExt.viewBinding
import com.example.cryto.presentation.adapter.CryptoListAdapter
import com.example.cryto.presentation.adapter.FavoritesAdapter
import com.example.cryto.presentation.adapter.holder.AdapterClick
import com.example.home.databinding.FragmentCryptoBinding

@AndroidEntryPoint
class CryptoFragment : BaseFragment<CryptoViewModel>(R.layout.fragment_crypto) {

    override val viewModel: CryptoViewModel by viewModels<CryptoViewModel>()

    private val binding by viewBinding(FragmentCryptoBinding::bind)

    private val adapter by lazy {
        CryptoListAdapter(adapterClick =  object :AdapterClick{

            override fun onClickStar(
                pairName: String,
                last: String,
                dailyPercent: String,
                pureDailyPercent: Double
            ) {
                viewModel.onClickStar(pairName,last,dailyPercent,pureDailyPercent)
            }

            override fun onClickItem() {

            }

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
                adapter.submitList(state.cryptoList)
                favoriteAdapter.submitList(state.favoritesList)
                binding.tvFavorites.isVisible = state.favoritesList.isNotEmpty()
            }
        }
    }

    private fun initUi() {
        viewModel.getCrypto()
        binding.apply {
            rvCryptoList.adapter = adapter
            rvFavorites.adapter = favoriteAdapter
            tvPairs.text = getString(R.string.pairs)
            tvFavorites.text = getString(R.string.favorites)
        }
    }


}