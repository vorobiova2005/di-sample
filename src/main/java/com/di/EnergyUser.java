package com.di;

public abstract class EnergyUser {
    private String name;
    private String address;

    public EnergyUser(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public abstract void displayUserInfo();
}
