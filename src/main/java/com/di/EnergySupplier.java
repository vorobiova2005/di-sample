package com.di;

public class EnergySupplier extends EnergyUser implements EnergyPricing {
    private double currentEnergyRate;

    public EnergySupplier(String name, String address) {
        super(name, address);
        this.currentEnergyRate = DEFAULT_RATE;
    }

    @Override
    public void updateEnergyRate(double newRate) {
        this.currentEnergyRate = newRate;
        System.out.println("Оновлено тариф на електроенергію: " + newRate + " грн/кВт·год");
    }

    @Override
    public double getCurrentEnergyRate() {
        return currentEnergyRate;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Постачальник: " + getName());
        System.out.println("Поточний тариф: " + getCurrentEnergyRate() + " грн/кВт·год");
    }
}
