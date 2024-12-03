package com.di;

import java.util.ArrayList;
import java.util.List;

public class EnergyConsumer extends EnergyUser {
    private double monthlyConsumption;
    private List<EnergyMeter> meters;

    public EnergyConsumer(String name, String address) {
        super(name, address);
        this.meters = new ArrayList<>();
    }

    public void addMeter(EnergyMeter meter) {
        meters.add(meter);
    }

    public double calculateMonthlyConsumption() {
        return meters.stream()
                     .mapToDouble(EnergyMeter::getCurrentReading)
                     .sum();
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Споживач: " + getName());
        System.out.println("Адреса: " + getAddress());
        System.out.println("Місячне споживання: " + calculateMonthlyConsumption() + " кВт/год");
    }
}
