package com.example.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.let { navigate(direction) }
}

fun NavController.safeNavigate(id: Int) {
    currentDestination?.getAction(id)?.let { navigate(id) }
}