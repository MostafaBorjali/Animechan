package ir.borjali.animechan.util

import android.content.Context
import ir.borjali.animechan.domain.model.AppResult
import ir.borjali.animechan.R

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

fun Context.noNetworkConnectivityError(): AppResult.Error {
    return AppResult.Error(Exception(this.resources.getString(R.string.no_network_connectivity)))
}

