package com.example.base.globalExt

infix fun Any.with(string: String): String {
    return "$this $string"
}