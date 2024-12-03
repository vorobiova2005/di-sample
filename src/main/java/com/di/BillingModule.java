package com.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

public class BillingModule extends AbstractModule {
    @Override
    protected void configure() {
        // Встановлюємо значення для EnergySupplier (мінімальний тариф)
        bind(Double.class)
            .annotatedWith(Names.named("Default Energy Rate"))
            .toInstance(1.5); // Ставимо дефолтний тариф
    }

    @Provides
    public EnergyConsumer provideEnergyConsumer() {
        // Створення споживача електроенергії
        return new EnergyConsumer("Іван Петров", "вул. Ропотліан, 42");
    }

    @Provides
    public EnergySupplier provideEnergySupplier() {
        // Створення постачальника електроенергії
        return new EnergySupplier("Київенерго", "вул. Хрещатик, 12");
    }
}


