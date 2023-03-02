package com.rysanek.dogpic.app

import android.app.Activity
import android.app.Application
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.rysanek.customviews.theme.ThemeManager.currentTheme
import com.rysanek.customviews.utils.ColorExtUtils.toColor
import com.rysanek.dogpic.ui.activities.MainActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DogPicApp: Application(), Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityPreCreated(activity: Activity, savedInstanceState: Bundle?) {
        super.onActivityPreCreated(activity, savedInstanceState)

        val statusBarColor  = if (activity is MainActivity) currentTheme.accentColor.toColor()
        else currentTheme.backgroundColor.toColor()

        activity.window?.setBackgroundDrawable(ColorDrawable(currentTheme.accentColor.toColor()))
        activity.window?.statusBarColor = statusBarColor
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }
}
