package com.example.cryto.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.example.base.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : CoreViewModel() {

}