package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Transaction> statement = new ArrayList<>();

    public static void main(String[] args) {

        loadStatement();
        int mainMenuCommand;

        do{
            System.out.println("Accounting Ledger Application Home");
            System.out.println("1) Add Deposit");
            System.out.println("2) Make Payment ");
            System.out.println("3) Ledger");
            System.out.println("0) Exit ");

            System.out.print("What would you like to do?");
            mainMenuCommand = scanner.nextInt();

            switch (mainMenuCommand){
                case 1:
                    handleAddDeposit();
                    break;
                case 2:
                    handleMakePayment();
                    break;
                case 3:
                    handleLedger();
                    break;
                case 0:
                    System.out.println("GoodBye");
                    break;
                default:
                    System.out.println("Invalid Input, Try Again.");

            }


        }while(mainMenuCommand != 0);


    }

    private static void loadStatement(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));

            String input;

            while ((input = bufferedReader.readLine()) != null){
                String[] fields = input.split("\\|");
                LocalDate date =  LocalDate.parse(fields[0]);
                LocalTime time =  LocalTime.parse(fields[1]);
                String description = fields[2];
                String vendor = fields[3];
                double amount = Double.parseDouble(fields[4]);

                Transaction transaction = new Transaction(date, time, description,vendor,amount);
                statement.add(transaction);
            }
            bufferedReader.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void handleAddDeposit() {
    }

    private static void handleMakePayment() {
    }

    private static void handleLedger() {

        int ledgerMenuCommand;
        do{
        System.out.println("Ledger Menu");
        System.out.println("1) All");
        System.out.println("2) Deposits ");
        System.out.println("3) Payments");
        System.out.println("4) Reports");
        System.out.println("0) Back to Home ");

        System.out.print("What would you like to do?");
        ledgerMenuCommand = scanner.nextInt();

        switch (ledgerMenuCommand){
            case 1:
                handleAll();
                break;
            case 2:
                handleDeposits();
                break;
            case 3:
                handlePayments();
                break;
            case 4:
                handleReports();
                break;
            case 0:
                System.out.println("Going Back to Home");
                break;
            default:
                System.out.println("Invalid Input, Try Again.");
        }

    }while(ledgerMenuCommand != 0);
    }

    private static void handleAll() {
    }

    private static void handleDeposits() {
    }

    private static void handlePayments() {
    }

    private static void handleReports() {
        int reportsMenuCommand;
        do{
            System.out.println("Reports Menu");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back to Ledger ");

            System.out.print("What would you like to do?");
            reportsMenuCommand = scanner.nextInt();

            switch (reportsMenuCommand){
                case 1:
                    handleMonthToDate();
                    break;
                case 2:
                    handlePreviousMonth();
                    break;
                case 3:
                    handleYearToDate();
                    break;
                case 4:
                   handlePreviousYear();
                    break;
                case 5:
                    handleSearchByVendor();
                    break;
                case 0:
                    System.out.println("Going Back to Home");
                    break;
                default:
                    System.out.println("Invalid Input, Try Again.");
            }

        }while(reportsMenuCommand != 0);

    }

    private static void handleMonthToDate() {
    }

    private static void handlePreviousMonth() {
    }

    private static void handleYearToDate() {
    }

    private static void handlePreviousYear() {
    }

    private static void handleSearchByVendor() {
    }

}