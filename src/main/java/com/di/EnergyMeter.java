package com.di;

import java.time.LocalDate;

public class EnergyMeter {
    private String meterId;
    private double currentReading;
    private LocalDate lastReadingDate;

    public EnergyMeter(String meterId) {
        this.meterId = meterId;
        this.currentReading = 0;
        this.lastReadingDate = LocalDate.now();
    }

    public void updateReading(double newReading) {
        this.currentReading = newReading;
        this.lastReadingDate = LocalDate.now();
    }

    public double getCurrentReading() {
        return currentReading;
    }

    public String getMeterId() {
        return meterId;
    }
}
