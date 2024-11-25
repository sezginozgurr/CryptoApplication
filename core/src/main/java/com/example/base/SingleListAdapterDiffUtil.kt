package com.example.base

import androidx.recyclerview.widget.DiffUtil

class SingleListAdapterDiffUtil<SID : SingleItemData> : DiffUtil.ItemCallback<SID>() {

    override fun areItemsTheSame(oldItem: SID, newItem: SID): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SID, newItem: SID): Boolean = oldItem==newItem
}