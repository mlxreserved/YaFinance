package com.example.workmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.domain.usecase.account.inter.SyncLocalChangesAccountUseCase
import com.example.domain.usecase.expense.inter.SyncLocalChangesExpenseUseCase
import com.example.domain.usecase.income.inter.SyncLocalChangesIncomesUseCase
import com.example.workmanager.di.FinanceWorkerFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FinanceWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val syncLocalChangesAccountUseCase: SyncLocalChangesAccountUseCase,
    private val syncLocalChangesExpenseUseCase: SyncLocalChangesExpenseUseCase,
    private val syncLocalChangesIncomesUseCase: SyncLocalChangesIncomesUseCase
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.d("WORKER", "is working")
        syncLocalChangesAccountUseCase.syncLocalChanges()
        syncLocalChangesIncomesUseCase.syncLocalChanges()
        syncLocalChangesExpenseUseCase.syncLocalChanges()

        return Result.success()
    }

    @AssistedFactory
    interface Factory : FinanceWorkerFactory {
        override fun create(appContext: Context, params: WorkerParameters): FinanceWorker
    }
}