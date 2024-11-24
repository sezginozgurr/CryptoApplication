package com.example.base.globalExt

infix fun Any.with(string: String): String {
    return "$this $string"
}

infix fun Any.withNoSpace(string: String): String {
    return "$this$string"
}