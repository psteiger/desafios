package com.cesar.psteiger.emailthreadreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class EmailThreadBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("EmailBroadcastReceiver", "onReceive ${intent?.extras?.getString("PROCESSED_EMAIL_THREAD")}")
        Toast.makeText(
                context,
                "Result: ${intent?.extras?.getString("PROCESSED_EMAIL_THREAD") ?: "None"}",
                Toast.LENGTH_LONG
        )
                .show()
    }
}