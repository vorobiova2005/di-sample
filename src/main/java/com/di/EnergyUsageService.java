package com.di;

import com.google.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Сервіс для роботи з даними про споживання енергії.
 */
public class EnergyUsageService {
    private final Connection connection;
    private EnergyConsumer energyConsumer;

    /**
     * Конструктор з впровадженням залежності від драйвера бази даних.
     *
     * @param connection з'єднання з базою даних
     */
    @Inject
    public EnergyUsageService(Connection connection) {
        this.connection = connection;
    }

    /**
     * Метод для збереження даних про споживання енергії.
     *
     * @param usageKwh використана електроенергія в кіловат-годинах
     * @param usageDate дата використання
     */
    public void processAndSaveEnergyUsage(double usageKwh, String usageDate) {
        if (energyConsumer == null) {
            throw new IllegalStateException("EnergyConsumer is not set.");
        }

        String sql = "INSERT INTO energy_usage (consumer_name, address, usage_kwh, usage_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, energyConsumer.getName());
            statement.setString(2, energyConsumer.getAddress());
            statement.setDouble(3, usageKwh);
            statement.setString(4, usageDate);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save energy usage", e);
        }
    }

    /**
     * Встановлює споживача енергії.
     *
     * @param energyConsumer об'єкт EnergyConsumer
     */
    @Inject
    public void setEnergyConsumer(EnergyConsumer energyConsumer) {
        this.energyConsumer = energyConsumer;
    }

    /**
     * Метод для виведення даних про енергоспоживання.
     */
    public void printEnergyUsage() {
        String sql = "SELECT consumer_name, address, usage_kwh, usage_date FROM energy_usage";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Дані про споживання енергії:");
            while (resultSet.next()) {
                System.out.printf("Ім'я: %s, Адреса: %s, Використано: %.2f кВт·год, Дата: %s%n",
                        resultSet.getString("consumer_name"),
                        resultSet.getString("address"),
                        resultSet.getDouble("usage_kwh"),
                        resultSet.getString("usage_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve energy usage data", e);
        }
    }
}

