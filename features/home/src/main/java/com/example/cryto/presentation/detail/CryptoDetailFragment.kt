package com.example.cryto.presentation.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.base.BaseFragment
import com.example.base.globalExt.viewBinding
import com.example.home.R
import com.example.home.databinding.FragmentCryptoDetailBinding
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoDetailFragment : BaseFragment<CryptoDetailViewModel>(R.layout.fragment_crypto_detail) {

    override val viewModel by viewModels<CryptoDetailViewModel>()

    private val binding by viewBinding(FragmentCryptoDetailBinding::bind)

    private lateinit var detailArgs: CryptoDetailArgument

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            detailArgs = CryptoDetailFragmentArgs.fromBundle(bundle).detailArgs
        }

        setChart()
    }

    private fun setChart() {
        val entries = ArrayList<CandleEntry>()
        entries.add(
            CandleEntry(
                0f,
                detailArgs.last.toFloat(),
                detailArgs.volume.toFloat(),
                detailArgs.bid.toFloat(),
                detailArgs.average.toFloat()
            )
        )

        val candleDataSet = CandleDataSet(entries, detailArgs.pairName).apply {
            color = Color.rgb(0, 255, 0)
            shadowColor = Color.GREEN
            valueTextColor = Color.WHITE
            valueTextColor = Color.WHITE
        }

        val candleData = CandleData(candleDataSet)
        binding.chart.data = candleData
        binding.chart.invalidate()
    }

}