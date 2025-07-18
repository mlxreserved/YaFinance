package com.example.workmanager.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.example.domain.usecase.account.inter.SyncLocalChangesAccountUseCase;
import com.example.domain.usecase.expense.inter.SyncLocalChangesExpenseUseCase;
import com.example.domain.usecase.income.inter.SyncLocalChangesIncomesUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class FinanceWorker_Factory {
  private final Provider<SyncLocalChangesAccountUseCase> syncLocalChangesAccountUseCaseProvider;

  private final Provider<SyncLocalChangesExpenseUseCase> syncLocalChangesExpenseUseCaseProvider;

  private final Provider<SyncLocalChangesIncomesUseCase> syncLocalChangesIncomesUseCaseProvider;

  public FinanceWorker_Factory(
      Provider<SyncLocalChangesAccountUseCase> syncLocalChangesAccountUseCaseProvider,
      Provider<SyncLocalChangesExpenseUseCase> syncLocalChangesExpenseUseCaseProvider,
      Provider<SyncLocalChangesIncomesUseCase> syncLocalChangesIncomesUseCaseProvider) {
    this.syncLocalChangesAccountUseCaseProvider = syncLocalChangesAccountUseCaseProvider;
    this.syncLocalChangesExpenseUseCaseProvider = syncLocalChangesExpenseUseCaseProvider;
    this.syncLocalChangesIncomesUseCaseProvider = syncLocalChangesIncomesUseCaseProvider;
  }

  public FinanceWorker get(Context context, WorkerParameters params) {
    return newInstance(context, params, syncLocalChangesAccountUseCaseProvider.get(), syncLocalChangesExpenseUseCaseProvider.get(), syncLocalChangesIncomesUseCaseProvider.get());
  }

  public static FinanceWorker_Factory create(
      Provider<SyncLocalChangesAccountUseCase> syncLocalChangesAccountUseCaseProvider,
      Provider<SyncLocalChangesExpenseUseCase> syncLocalChangesExpenseUseCaseProvider,
      Provider<SyncLocalChangesIncomesUseCase> syncLocalChangesIncomesUseCaseProvider) {
    return new FinanceWorker_Factory(syncLocalChangesAccountUseCaseProvider, syncLocalChangesExpenseUseCaseProvider, syncLocalChangesIncomesUseCaseProvider);
  }

  public static FinanceWorker newInstance(Context context, WorkerParameters params,
      SyncLocalChangesAccountUseCase syncLocalChangesAccountUseCase,
      SyncLocalChangesExpenseUseCase syncLocalChangesExpenseUseCase,
      SyncLocalChangesIncomesUseCase syncLocalChangesIncomesUseCase) {
    return new FinanceWorker(context, params, syncLocalChangesAccountUseCase, syncLocalChangesExpenseUseCase, syncLocalChangesIncomesUseCase);
  }
}
