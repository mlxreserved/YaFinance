package com.example.workmanager.di;

import androidx.work.ListenableWorker;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Map;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DaggerWorkerFactory_Factory implements Factory<DaggerWorkerFactory> {
  private final Provider<Map<Class<? extends ListenableWorker>, FinanceWorkerFactory>> workerFactoriesProvider;

  public DaggerWorkerFactory_Factory(
      Provider<Map<Class<? extends ListenableWorker>, FinanceWorkerFactory>> workerFactoriesProvider) {
    this.workerFactoriesProvider = workerFactoriesProvider;
  }

  @Override
  public DaggerWorkerFactory get() {
    return newInstance(workerFactoriesProvider.get());
  }

  public static DaggerWorkerFactory_Factory create(
      Provider<Map<Class<? extends ListenableWorker>, FinanceWorkerFactory>> workerFactoriesProvider) {
    return new DaggerWorkerFactory_Factory(workerFactoriesProvider);
  }

  public static DaggerWorkerFactory newInstance(
      Map<Class<? extends ListenableWorker>, FinanceWorkerFactory> workerFactories) {
    return new DaggerWorkerFactory(workerFactories);
  }
}
