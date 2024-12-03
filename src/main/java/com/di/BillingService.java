package com.di;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class BillingService {
    private EnergyConsumer consumer;
    private EnergySupplier supplier;
    private double energyRate;

    // Закоментований конструктор
    // @Inject
    // public BillingService(EnergyConsumer consumer,
    //                       EnergySupplier supplier,
    //                       @Named("Default Energy Rate") double energyRate) {
    //     this.consumer = consumer;
    //     this.supplier = supplier;
    //     this.energyRate = energyRate;
    // }

    // Використовуємо setter-методи для впровадження залежностей
    @Inject
    public void setEnergyConsumer(EnergyConsumer consumer) {
        this.consumer = consumer;
    }

    @Inject
    public void setEnergySupplier(EnergySupplier supplier) {
        this.supplier = supplier;
    }

    @Inject
    public void setEnergyRate(@Named("Default Energy Rate") double energyRate) {
        this.energyRate = energyRate;
    }

    public void generateBill() {
        double totalConsumption = consumer.calculateMonthlyConsumption();
        double totalCost = totalConsumption * energyRate; // Використовуємо тариф для обчислення

        System.out.println("Рахунок за електроенергію");
        System.out.println("Споживач: " + consumer.getName());
        System.out.println("Постачальник: " + supplier.getName());
        System.out.println("Спожито електроенергії: " + totalConsumption + " кВт/год");
        System.out.println("Тариф: " + energyRate + " грн/кВт·год");
        System.out.println("Загальна вартість: " + totalCost + " грн");
    }
}


