package com.example.workmanager.workManager;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("com.example.utils.qualifiers.ApplicationContext")
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
public final class FinanceWorkManager_Factory implements Factory<FinanceWorkManager> {
  private final Provider<Context> contextProvider;

  public FinanceWorkManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public FinanceWorkManager get() {
    return newInstance(contextProvider.get());
  }

  public static FinanceWorkManager_Factory create(Provider<Context> contextProvider) {
    return new FinanceWorkManager_Factory(contextProvider);
  }

  public static FinanceWorkManager newInstance(Context context) {
    return new FinanceWorkManager(context);
  }
}
