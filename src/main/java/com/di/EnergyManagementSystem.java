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

        // Додатковий функціонал: робота з енергоспоживанням
        // Отримуємо сервіс для роботи з енергоспоживанням
        EnergyUsageService energyUsageService = injector.getInstance(EnergyUsageService.class);

        // Зберігаємо зразок даних
        energyUsageService.processAndSaveEnergyUsage(150.75, "2024-12-01");

        // Виводимо дані
        energyUsageService.printEnergyUsage();
    }
}
