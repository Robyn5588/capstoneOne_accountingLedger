package com.ps;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate dateStamp;
    private LocalTime timeStamp;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDate dateStamp, LocalTime timeStamp, String description, String vendor, double amount) {
        this.dateStamp = dateStamp;
        this.timeStamp = timeStamp;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return dateStamp;
    }

    public void setDate(LocalDate dateStamp) {
        this.dateStamp = dateStamp;
    }

    public LocalTime getTime() {
        return timeStamp;
    }

    public void setTime(LocalTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + dateStamp + '\'' +
                ", time='" + timeStamp + '\'' +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                '}';
    }
}

