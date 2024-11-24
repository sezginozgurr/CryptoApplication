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
import com.example.home.databinding.FragmentCryptoBinding

@AndroidEntryPoint
class CryptoFragment : BaseFragment<CryptoViewModel>(R.layout.fragment_crypto) {

    override val viewModel: CryptoViewModel by viewModels<CryptoViewModel>()

    private val binding by viewBinding(FragmentCryptoBinding::bind)

    private val adapter by lazy { CryptoListAdapter {  } }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //collect()
        //viewModel.getCrypto()
    }

    private fun collect() {
        launchRepeatWithViewLifecycle {
            viewModel.uiState.collect { state ->
                //adapter.submitList(state.list)
            }
        }
    }


}