package com.example.cryto.presentation.listscreen.adapter.holder

import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.base.globalExt.with
import com.example.cryto.presentation.listscreen.adapter.itemdata.CryptoDataItem
import com.example.extension.formatLast
import com.example.extension.formatWithMaxTwoAndThreeDigits
import com.example.extension.percentage
import com.example.home.R
import com.example.home.databinding.ItemCryptoBinding

class CryptoHolder(
    private val binding: ItemCryptoBinding,
    private val adapterClick: AdapterClick
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemData: CryptoDataItem) {
        with(binding) {
            tvPairName.text = itemData.pairNormalized
            tvLast.text = itemData.last.formatLast()

            val formattedDailyPercent = itemData.dailyPercent
            tvDailyPercent.text = formattedDailyPercent.percentage()
            tvDailyPercent.setTextColor(itemView.context.getColor(itemData.dailyPercentColor))

            tvDaily.text = itemData.volume.formatWithMaxTwoAndThreeDigits() with itemData.numeratorSymbol

            itemView.setOnClickListener {
                adapterClick.onClickItem(
                    pairName = itemData.pairNormalized,
                    last = itemData.last,
                    high = itemData.high,
                    low = itemData.low,
                    bid = itemData.bid,
                    ask = itemData.bid,
                    open = itemData.open,
                    volume = itemData.volume,
                    average = itemData.average
                )
            }

            updateStarIcon(itemData.isFavorite)

            ivStar.setOnClickListener {
                itemData.isFavorite = itemData.isFavorite.not()

                adapterClick.onClickStar(
                    pairName = itemData.pairNormalized,
                    last = itemData.last.formatLast(),
                    dailyPercent = itemData.dailyPercent,
                    pureDailyPercent = itemData.pureDailyPercent,
                )

                updateStarIcon(itemData.isFavorite)
            }
        }
    }

    private fun updateStarIcon(isSelected: Boolean) {
        val starColor = if (isSelected) R.color.favorite_yellow else R.color.white
        val drawable = ContextCompat.getDrawable(itemView.context, R.drawable.baseline_star)

        drawable?.let {
            DrawableCompat.setTint(
                it,
                ContextCompat.getColor(itemView.context, starColor)
            )
            binding.ivStar.setImageDrawable(it)
        }
    }
}