package com.example.base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.base.delegation.HandleErrorDelegation
import com.example.base.delegation.HandleErrorDelegationImpl
import com.example.base.globalExt.launchRepeatWithViewLifecycle
import com.example.base.model.NetworkState
import kotlinx.coroutines.launch


abstract class BaseFragment<out VM : CoreViewModel>(
    @LayoutRes resId: Int) : Fragment(resId),
    HandleErrorDelegation by HandleErrorDelegationImpl() {


    abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachViewModels(viewModel)
    }

    private fun attachViewModels(vararg viewModels: CoreViewModel) {
        launchRepeatWithViewLifecycle {
                viewModel.networkStateFlow.collect { state ->
                    when (state) {
                        is NetworkState.Initial -> {
                            // no-op
                        }
                        is NetworkState.Error -> {
                            errorDelegation(
                                fragment= this@BaseFragment,
                                errorState = state)
                        }
                        is NetworkState.Failure -> {
                            with(state) {
                                handleFailureDelegation(
                                    fragment= this@BaseFragment,
                                    statusCode = error.statusCode,
                                    message = error.message,
                                    messageType = messageType,
                                    payload = error.payload
                                )
                            }
                        }
                        is NetworkState.Loading -> {
                            requireActivity().runOnUiThread {
                                viewLifecycleOwner.lifecycleScope.launch {
                                    handleLoadingDelegation(
                                        requireContext(),
                                        state.loadingType,
                                        state.loadingText.orEmpty()
                                    )
                                }
                            }

                        }
                        is NetworkState.Success -> {
                            handleSuccessDelegation(
                                fragment = this@BaseFragment,
                                message = state.response.message.orEmpty(),
                                messageType = state.messageType
                            )

                        }
                    }
                }

        }
    }

    override fun onResume() {
        super.onResume()
        Log.e("currentFragment",javaClass.name)
    }

}
