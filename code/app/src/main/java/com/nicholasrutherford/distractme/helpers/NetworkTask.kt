package com.nicholasrutherford.distractme.helpers

import android.app.Dialog
import android.os.AsyncTask
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.activitys.MainActivity

class NetworkTask(var activity: MainActivity) : AsyncTask<Void, Void, Void>() {

    var dialog = Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar)

    override fun onPreExecute() {
        val view = activity.layoutInflater.inflate(R.layout.full_screen_progress, null)
        dialog.setContentView(view)
        dialog.setCancelable(false)
        dialog.show()
        super.onPreExecute()
    }
    override fun doInBackground(vararg params: Void?): Void? {
        Thread.sleep(1000)
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        dialog.dismiss()
    }

}