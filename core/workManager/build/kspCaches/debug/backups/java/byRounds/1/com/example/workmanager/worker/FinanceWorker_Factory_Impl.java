package com.example.workmanager.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class FinanceWorker_Factory_Impl implements FinanceWorker.Factory {
  private final FinanceWorker_Factory delegateFactory;

  FinanceWorker_Factory_Impl(FinanceWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public FinanceWorker create(Context appContext, WorkerParameters params) {
    return delegateFactory.get(appContext, params);
  }

  public static Provider<FinanceWorker.Factory> create(FinanceWorker_Factory delegateFactory) {
    return InstanceFactory.create(new FinanceWorker_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<FinanceWorker.Factory> createFactoryProvider(
      FinanceWorker_Factory delegateFactory) {
    return InstanceFactory.create(new FinanceWorker_Factory_Impl(delegateFactory));
  }
}
