package edu.singaporetech.services

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import edu.singaporetech.services.databinding.ActivityServiceBinding

/**
 * CSC2007 Lab 05
 *
 * Author: your teacher
 * Updated: the day before your lab.
 *
 * TODO Add a Service (beyond this file) to constantly generate random numbers as specified in the
 *      lab sheet. You get bonus l33t stars (not real marks sorry...) if you use coroutines for
 *      threading in your Service.
 */
class ServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServiceBinding
    private var randomService: RandomService? = null
    private var isBound = false

    private val primeWorkManager = WorkManager.getInstance(application)

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as RandomService.LocalBinder
            randomService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            randomService = null
            isBound = false
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, RandomService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
        startService(intent)

        binding.buttonRandom.setOnClickListener {
            val lastRandomNumber = randomService?.getLastRandomNumber()
            binding.textEditPrime.setText(lastRandomNumber.toString())
            Log.d(TAG, "Last random number: $lastRandomNumber")
        }

        binding.buttonPrime.setOnClickListener {
            val text = binding.textEditPrime.text
            if (text != null && text.isNotEmpty()) {
                val constraints = Constraints.Builder()
                    .setRequiresBatteryNotLow(true)
                    .build()
                val inputData = Data.Builder().putLong("number", text.toString().toLong()).build()
                val primeWorkRequest = OneTimeWorkRequestBuilder<PrimeWorker>()
                    .setConstraints(constraints)
                    .setInputData(inputData)
                    .build()
                primeWorkManager.enqueueUniqueWork(
                    "prime",
                    ExistingWorkPolicy.REPLACE,
                    primeWorkRequest
                )
                primeWorkManager.getWorkInfoByIdLiveData(primeWorkRequest.id)
                    .observe(this) { workInfo ->
                        if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                            val output = workInfo.outputData.getString("output")
                            binding.textViewResult.text = output
                            Log.d(TAG, "Prime result: $output")
                        }
                    }
            } else {
                Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }

    companion object {
        private const val TAG = "ServiceActivity"
    }
}