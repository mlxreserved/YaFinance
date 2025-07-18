package com.example.workmanager.di

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

interface FinanceWorkerFactory {
    fun create(appContext: Context, params: WorkerParameters): ListenableWorker
}