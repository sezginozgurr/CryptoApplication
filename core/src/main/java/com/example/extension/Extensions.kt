package com.example.extension

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectIn(coroutineScope: CoroutineScope, function: (T) -> Unit) {
    coroutineScope.launch {
        collect {
            function(it)
        }
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun String.percentage(): String {
    return "%$this"
}

fun Double.formatLast(): String {
    val numberString = this.toString()
    val parts = numberString.split(".")
    val integerPart = parts[0]

    val relevantPart = if (integerPart.length > 3) {
        integerPart.substring(0, 3)
    } else {
        integerPart
    }

    val decimalPart = if (parts.size > 1) {
        "." + parts[1]
    } else {
        ""
    }

    val result = "$relevantPart$decimalPart".toDouble()

    return String.format("%.2f", result).replace('.', ',')
}

fun Double.formatWithMaxTwoAndThreeDigits(): String {
    val numberString = this.toString()
    val parts = numberString.split(".")
    val integerPart = parts[0]

    val relevantPart = if (integerPart.length > 2) {
        integerPart.substring(0, 2)
    } else {
        integerPart
    }

    val decimalPart = if (parts.size > 1) {
        "." + parts[1].take(3)
    } else {
        ""
    }

    return relevantPart + decimalPart
}


fun hideKeyboard(activity: Activity, view: View) {
    val inputMethodManager =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}