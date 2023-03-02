package com.rysanek.dogpic.ui.activities.baseactivity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.KeyEvent
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.rysanek.customviews.theme.ThemeManager.currentTheme
import com.rysanek.customviews.theme.ThemeManager.switchColorTheme
import com.rysanek.customviews.utils.ColorExtUtils.toColor
import com.rysanek.dogpic.R

abstract class DogPicActivity: AppCompatActivity() {

    private var progressDialog: AlertDialog? = null

    fun showLoadingSpinner() {

        progressDialog ?: run {
            progressDialog = AlertDialog.Builder(this).create()
        }

        progressDialog?.let { dialog ->

            with(dialog) {
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setCancelable(false)
                if (!isFinishing) show()
                setContentView(R.layout.indeterminate_progress_screen)
                dialog.findViewById<ProgressBar>(R.id.mainProgressBar)
                    ?.indeterminateDrawable?.mutate()?.setTint(currentTheme.buttonBgColor.toColor())
            }
        }

    }

    fun hideLoadingSpinner() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    fun setCurrentWindowColors(statusBarColor: Int? = null, windowColor: Int? = null) {
        statusBarColor?.let { color -> window?.statusBarColor = color }
        windowColor?.let { color -> window?.setBackgroundDrawable(ColorDrawable(color)) }
    }

    override fun onBackPressed() {
        findViewById<ConstraintLayout>(R.id.backArrow)?.performClick() ?: run {

        }
        super.onBackPressed()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACKSLASH){ switchColorTheme() }
        return super.onKeyDown(keyCode, event)
    }
}