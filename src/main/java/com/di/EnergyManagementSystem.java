package com.di;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class EnergyManagementSystem {
    public static void main(String[] args) {
        // Створюємо інжектор Guice з BillingModule
        Injector injector = Guice.createInjector(new BillingModule());

        // Отримуємо екземпляр BillingService з впровадженими залежностями
        BillingService billingService = injector.getInstance(BillingService.class);

        // Генеруємо рахунок
        billingService.generateBill();
    }
}
