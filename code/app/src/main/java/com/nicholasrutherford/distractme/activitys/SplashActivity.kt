package com.nicholasrutherford.distractme.activitys

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.helpers.Typeface
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by Nick R.
 */

class SplashActivity : AppCompatActivity() {

    private val typeface = Typeface()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        delayHandlerForSplash()
        setTypeFaceOfSplashTitle()
    }

    private fun startUpMainActivity() {
        val intent: Intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

    private fun delayHandlerForSplash() {
        val handler = Handler()
        handler.postDelayed({
            startUpMainActivity()
        }, 5000)
        }

    private fun setTypeFaceOfSplashTitle() {
        typeface.setTypefaceForHeaderBold(tvSplashTitle,applicationContext)
    }

    }