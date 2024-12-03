package com.di;

public interface EnergyPricing {
    double DEFAULT_RATE = 1.5; // грн за кВт/год
    
    void updateEnergyRate(double newRate);
    double getCurrentEnergyRate();
}
