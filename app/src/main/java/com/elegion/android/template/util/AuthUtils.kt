package com.elegion.android.template.util

import android.content.Context
import android.content.Intent

import com.elegion.android.template.data.Repository
import com.elegion.android.template.ui.login.LoginActivity

object AuthUtils {

    @JvmStatic
    fun logout(repository: Repository) = repository.logout()

    @JvmStatic
    fun openLogin(context: Context) {
        val intent = LoginActivity.makeIntent(context)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}
