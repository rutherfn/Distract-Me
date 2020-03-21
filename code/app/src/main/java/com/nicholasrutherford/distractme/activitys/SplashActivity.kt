package com.nicholasrutherford.distractme.activitys

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.distractme.R

/**
 * Created by Nick R.
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        delayHandlerForSplash()
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

    }