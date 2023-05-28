package edu.singaporetech.services

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.system.measureTimeMillis

class PrimeWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val number = inputData.getLong("number", 0)
        val timeTaken = measureTimeMillis {
            isPrime(number)
        }
        val isPrime = isPrime(number)
        val output = if (isPrime) {
            "$number is PRIME"
        } else {
            "$number is not PRIME"
        }
        val outputWithTime = "$output (" + "$timeTaken" + "ms)"
        val outputData = Data.Builder().putString("output", outputWithTime).build()
        return Result.success(outputData)
    }

    private fun isPrime(num: Long): Boolean {
        for (i in 2 until num) {
            if (num % i == 0L) {
                return false
            }
        }
        return true
    }
}