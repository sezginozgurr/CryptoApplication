package com.example.cryto.presentation.listscreen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.base.BaseFragment
import com.example.base.globalExt.launchRepeatWithViewLifecycle
import com.example.base.globalExt.viewBinding
import com.example.cryto.presentation.detail.CryptoDetailArgument
import com.example.cryto.presentation.listscreen.adapter.CryptoListAdapter
import com.example.cryto.presentation.listscreen.adapter.FavoritesAdapter
import com.example.cryto.presentation.listscreen.adapter.holder.AdapterClick
import com.example.home.R
import com.example.home.databinding.FragmentCryptoBinding
import com.example.navigation.safeNavigate
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CryptoFragment : BaseFragment<CryptoViewModel>(R.layout.fragment_crypto) {

    override val viewModel: CryptoViewModel by viewModels<CryptoViewModel>()

    private val binding by viewBinding(FragmentCryptoBinding::bind)

    private val adapter by lazy {
        CryptoListAdapter(adapterClick = object : AdapterClick {

            override fun onClickStar(
                pairName: String,
                last: String,
                dailyPercent: String,
                pureDailyPercent: Double
            ) {
                viewModel.onClickStar(
                    pairName = pairName,
                    last = last,
                    dailyPercent = dailyPercent,
                    pureDailyPercent = pureDailyPercent
                )
            }

            override fun onClickItem(
                pairName: String,
                last: Double,
                high: Double,
                low: Double,
                bid: Double,
                ask: Double,
                open: Double,
                volume: Double,
                average: Double
            ) {
                val detailArgs = CryptoDetailArgument(
                    pairName = pairName,
                    last = last,
                    high = high,
                    low = low,
                    bid = bid,
                    ask = ask,
                    open = open,
                    volume = volume,
                    average = average
                )
                val action =
                    CryptoFragmentDirections.actionCryptoFragmentToCryptoDetailFragment(detailArgs)

                findNavController().safeNavigate(action)
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
            rvFavorites.itemAnimator = MyItemAnimator()

            rvFavorites.adapter = favoriteAdapter
            rvCryptoList.itemAnimator = MyItemAnimator()

            tvPairs.text = getString(R.string.pairs)
            tvFavorites.text = getString(R.string.favorites)
        }
    }

}