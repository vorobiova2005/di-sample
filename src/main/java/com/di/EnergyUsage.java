package com.di;

/**
 * Клас для опису споживання енергії.
 */
public class EnergyUsage {
    private final String consumerName;
    private final String address;
    private final double usageKwh;
    private final String usageDate;

    public EnergyUsage(String consumerName, String address, double usageKwh, String usageDate) {
        this.consumerName = consumerName;
        this.address = address;
        this.usageKwh = usageKwh;
        this.usageDate = usageDate;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public String getAddress() {
        return address;
    }

    public double getUsageKwh() {
        return usageKwh;
    }

    public String getUsageDate() {
        return usageDate;
    }
}
