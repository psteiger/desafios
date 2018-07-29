package com.cesar.psteiger.emailthreadreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val receiver = EmailThreadBroadcastReceiver()
    private val emailThread = "1 2 3 4 2 3 2 5 2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helloWorldTextView.text = "Sending email thread $emailThread to other app service."
    }


    override fun onStart() {
        super.onStart()

        val intent = Intent("com.cesar.psteiger.emailprocessorservice.PROCESS_EMAIL_THREAD").apply {
            setPackage("com.cesar.psteiger.emailprocessorservice")
            putExtra("EMAIL_THREAD", emailThread)
        }

        val intentFilter = IntentFilter("com.cesar.psteiger.emailprocessorservice.PROCESSED_EMAIL_THREAD")
        registerReceiver(receiver, intentFilter)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startForegroundService(intent)
        else
            startService(intent)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}
