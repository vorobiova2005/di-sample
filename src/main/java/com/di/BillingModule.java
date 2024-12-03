package com.di;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BillingModule extends AbstractModule {
    @Override
    protected void configure() {
        // Встановлюємо значення для EnergySupplier (мінімальний тариф)
        bind(Double.class)
            .annotatedWith(Names.named("Default Energy Rate"))
            .toInstance(1.5); // Ставимо дефолтний тариф

        // Встановлюємо JDBC URL для бази даних
        bind(String.class)
            .annotatedWith(Names.named("JDBC URL"))
            .toInstance("jdbc:sqlite:target/energy_consumption.db"); // Задаємо назву бази даних
    }

    @Provides
    public EnergyConsumer provideEnergyConsumer() {
        // Створення споживача електроенергії
        return new EnergyConsumer("Іван Петров", "вул. Ропотліан, 42");
    }

    @Provides
    public EnergySupplier provideEnergySupplier() {
        // Створення постачальника електроенергії
        return new EnergySupplier("Київенерго", "вул. Хрещатик, 12");
    }

    @Provides
    @Singleton
    Connection provideConnection(@Named("JDBC URL") String url) {
        try {
            Connection connection = DriverManager.getConnection(url);
            createTableIfNotExists(connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    /**
     * Створює таблицю "energy_usage" у базі даних, якщо вона ще не існує.
     * Таблиця містить такі колонки:
     * - consumer_name: TEXT, ім'я споживача.
     * - address: TEXT, адреса споживача.
     * - usage_kwh: REAL, кількість використаної електроенергії у кіловат-годинах.
     * - usage_date: TEXT, дата використання.
     *
     * @param connection з'єднання з базою даних, яке використовується для створення таблиці.
     * @throws RuntimeException у разі виникнення помилки під час створення таблиці.
     */
    private void createTableIfNotExists(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS energy_usage (" +
                                "consumer_name TEXT NOT NULL, " +
                                "address TEXT NOT NULL, " +
                                "usage_kwh REAL NOT NULL, " +
                                "usage_date TEXT NOT NULL)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create table", e);
        }
    }
}