package com.example.database.di;

import com.example.database.dao.IncomeDao;
import com.example.database.database.FinanceDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class DatabaseModule_ProvideIncomeDaoFactory implements Factory<IncomeDao> {
  private final Provider<FinanceDatabase> databaseProvider;

  public DatabaseModule_ProvideIncomeDaoFactory(Provider<FinanceDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public IncomeDao get() {
    return provideIncomeDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideIncomeDaoFactory create(
      Provider<FinanceDatabase> databaseProvider) {
    return new DatabaseModule_ProvideIncomeDaoFactory(databaseProvider);
  }

  public static IncomeDao provideIncomeDao(FinanceDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideIncomeDao(database));
  }
}
