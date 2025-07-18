package com.example.workmanager.workManager

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.utils.qualifiers.ApplicationContext
import com.example.workmanager.worker.FinanceWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FinanceWorkManager @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    private val syncConstraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    fun schedulePeriodicSync() {
        val request = PeriodicWorkRequestBuilder<FinanceWorker>(
            repeatInterval = 2,
            repeatIntervalTimeUnit = TimeUnit.HOURS,
            flexTimeInterval = 15,
            flexTimeIntervalUnit = TimeUnit.MINUTES
        )
            .setConstraints(syncConstraints)
            .build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "periodic_sync",
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
    }
}