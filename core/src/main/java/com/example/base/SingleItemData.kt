package com.example.base

interface SingleItemData {

    val id: Any

    override fun equals(other: Any?): Boolean

    override fun hashCode():Int
}