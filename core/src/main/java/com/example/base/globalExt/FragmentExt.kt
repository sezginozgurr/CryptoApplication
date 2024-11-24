package com.example.base.globalExt

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

inline fun Fragment.launchRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}

inline fun AppCompatActivity.launchRepeatWithViewLifecycle(
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launchWhenStarted {
        block()
    }
}
/*
fun NavController.getNavOptions(): NavOptions.Builder {
    return NavOptions.Builder()
        .setEnterAnim(com.performa.uikit.widget.R.anim.nav_default_enter_anim)
        .setExitAnim(com.performa.uikit.widget.R.anim.nav_default_exit_anim)
        .setPopEnterAnim(com.performa.uikit.widget.R.anim.nav_default_pop_enter_anim)
        .setPopExitAnim(com.performa.uikit.widget.R.anim.nav_default_pop_exit_anim)
}


fun <T> NavController.getNavigationResult(key: String = "result") = currentBackStackEntry?.savedStateHandle?.get<T>(key)

fun <T> NavController.setNavigationResult(result: T, key: String = "result") { previousBackStackEntry?.savedStateHandle?.set(key, result) }

fun <T> NavController.setCurrentNavigationResult(result: T, key: String = "result") { currentBackStackEntry?.savedStateHandle?.set(key, result) }

fun <T> NavController.setNavigationResult(result: T, key: String = "result", destinationId: Int) {
    try {
        getBackStackEntry(destinationId).savedStateHandle[key] = result
    } catch (_: Exception) { }
}

fun <T> NavController.peekNavigationResult(key: String = "result") = getNavigationResult<T>(key)?.let {
    setCurrentNavigationResult(null, key); it
}
 */
fun Fragment.shareCode(inviteCode:String){

    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, inviteCode)

    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}
fun Fragment.shareText(text:String,packageName:String,launcher:ActivityResultLauncher<Intent>){
    var sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    try {
        sendIntent.setPackage(packageName)
        launcher.launch(sendIntent)

    }catch (e:ActivityNotFoundException){
        val shareIntent = Intent.createChooser(sendIntent, null)
        launcher.launch(shareIntent)
    }

}