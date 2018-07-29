package com.cesar.psteiger.emailprocessorservice

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_INCLUDE_STOPPED_PACKAGES
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_MIN

class EmailProcessorService : IntentService("EmailProcessorService") {

    private val String.toLinkedListOfInt: MyLinkedList<Int>?
        get() {
            if (length < 1) return null

            var result: MyLinkedList<Int>? = null

            split(' ').forEach {
                if (result == null)
                    result = MyLinkedList(it.toInt())
                else
                    result!!.add(it.toInt())
            }

            return result
        }

    private fun myStartForeground() {
        val channelId =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    createNotificationChannel()
                } else {
                    // If earlier version channel ID is not used
                    // https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#NotificationCompat.Builder(android.content.Context)
                    ""
                }

        val notificationBuilder = NotificationCompat.Builder(this, channelId )
        val notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(PRIORITY_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build()
        startForeground(101, notification)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String{
        val channelId = "my_service"
        val channelName = "My Background Service"
        val chan = NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_NONE)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

    override fun onCreate() {
        super.onCreate()
        myStartForeground()
    }

    override fun onHandleIntent(p0: Intent?) {
        p0?.extras?.getString("EMAIL_THREAD")?.let {
            Log.e("onHandleIntent", "Received linked list: $it")

            it.toLinkedListOfInt?.run {
                removeDup()

                sendBroadcast(Intent("com.cesar.psteiger.emailprocessorservice.PROCESSED_EMAIL_THREAD").apply {
                    //setPackage("com.cesar.psteiger.emailprocessorservice")
                    addFlags(FLAG_INCLUDE_STOPPED_PACKAGES)
                    putExtra("PROCESSED_EMAIL_THREAD", this@run.toString())
                })
            }
        }
    }
}