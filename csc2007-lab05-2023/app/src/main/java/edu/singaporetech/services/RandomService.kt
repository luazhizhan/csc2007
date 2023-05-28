package edu.singaporetech.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*
import java.util.*

class RandomService : Service() {
    private val binder = LocalBinder()
    private var job: Job? = null
    private val randomNumberList = mutableListOf<Int>()

    inner class LocalBinder : Binder() {
        fun getService(): RandomService = this@RandomService
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind()")
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand()")
        generateRandomNumber()
        return START_STICKY
    }

    private fun generateRandomNumber() {
        job = GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                val random = Random().nextInt(899999999) + 100000000
                randomNumberList.add(random)
                Log.d(TAG, "RandomService: $random added")
                delay(1000)
            }
        }
    }

    fun getLastRandomNumber(): Int? {
        return randomNumberList.lastOrNull()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
        job?.cancel()
    }

    companion object {
        private const val TAG = "RandomService"
    }
}