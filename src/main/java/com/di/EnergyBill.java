package com.di;

import java.time.LocalDate;

public class EnergyBill {
    private EnergyConsumer consumer;
    private EnergySupplier supplier;
    private LocalDate billDate;
    private double totalConsumption;
    private double totalCost;

    public EnergyBill(EnergyConsumer consumer, EnergySupplier supplier) {
        this.consumer = consumer;
        this.supplier = supplier;
        this.billDate = LocalDate.now();
        calculateBill();
    }

    private void calculateBill() {
        totalConsumption = consumer.calculateMonthlyConsumption();
        totalCost = totalConsumption * supplier.getCurrentEnergyRate();
    }

    public void displayBillDetails() {
        System.out.println("Рахунок за електроенергію");
        System.out.println("Дата: " + billDate);
        System.out.println("Споживач: " + consumer.getName());
        System.out.println("Постачальник: " + supplier.getName());
        System.out.println("Спожито електроенергії: " + totalConsumption + " кВт/год");
        System.out.println("Тариф: " + supplier.getCurrentEnergyRate() + " грн/кВт·год");
        System.out.println("Загальна вартість: " + totalCost + " грн");
    }
}
