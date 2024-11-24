package com.example.base.dialog

import android.content.Context
import androidx.core.view.isVisible
import com.example.base.globalExt.layoutInflater
import com.example.core.databinding.FragmentLoadingDilaogBinding

class LoadingDialog(
    private val context: Context,
    private val message: String
) : BaseDialog<FragmentLoadingDilaogBinding>(context) {

    override fun initView() {
        binding?.dialogText?.run {
            isVisible = message.isNotEmpty()
            text = message
        }
    }

    init {
        initDialog()
    }

    override fun getViewBinding() = context.layoutInflater.let { inf->
        FragmentLoadingDilaogBinding.inflate(inf)
    }


}