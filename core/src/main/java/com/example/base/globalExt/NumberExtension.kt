package com.example.base.globalExt

import android.content.res.Resources
import android.util.TypedValue
import java.math.BigDecimal
import kotlin.math.roundToInt

val Int?.orZero: Int
    get() = this ?: 0

val Long?.orZero: Long
    get() = this ?: 0L

val Float?.orZero: Float
    get() = this ?: .0f

val Double?.orZero: Double
    get() = this ?: 0.0

val BigDecimal?.orZero: BigDecimal
    get() = this ?: BigDecimal.ZERO

val Int?.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.orZero.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

val Float?.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.orZero,
        Resources.getSystem().displayMetrics
    )

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()
