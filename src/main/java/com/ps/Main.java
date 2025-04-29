package com.ps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
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

            System.out.print("What would you like to do? ");
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
                LocalDate dateStamp = LocalDate.parse(fields[0]);
                LocalTime timeStamp =  LocalTime.parse(fields[1]);
                String description = fields[2];
                String vendor = fields[3];
                double amount = Double.parseDouble(fields[4]);

                Transaction transaction = new Transaction(dateStamp, timeStamp, description,vendor,amount);
                statement.add(transaction);
            }
            bufferedReader.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void handleAddDeposit() {
        LocalDate dateDeposit = LocalDate.now();
        LocalTime timeDeposit = LocalTime.now();

        scanner.nextLine();
        System.out.print("Enter Description: ");
        String writeDescription = scanner.nextLine();

        System.out.print("Enter Vendor: ");
        String writeVendor = scanner.nextLine();

        System.out.print("Enter Amount: ");
        double writeAmount = scanner.nextDouble();
        writeAmount = Math.abs(writeAmount);


        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));

            String formattedTransaction = String.format("%tF|%tT|%s|%s|%.2f\n",dateDeposit,timeDeposit, writeDescription,writeVendor, writeAmount);
            bufferedWriter.write(formattedTransaction);

            bufferedWriter.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    private static void handleMakePayment() {

        LocalDate datePayment = LocalDate.now();
        LocalTime timePayment = LocalTime.now();

        scanner.nextLine();
        System.out.print("Enter Description: ");
        String writeDescription = scanner.nextLine();

        System.out.print("Enter Vendor: ");
        String writeVendor = scanner.nextLine();

        System.out.print("Enter Amount: ");
        double writeAmount = scanner.nextDouble();
        writeAmount = -Math.abs(writeAmount);

        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv",true));

                String formattedTransaction = String.format("%tF|%tT|%s|%s|%.2f\n",datePayment,timePayment, writeDescription,writeVendor, writeAmount);
                bufferedWriter.write(formattedTransaction);

            bufferedWriter.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
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

        System.out.print("What would you like to do? ");
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

        statement.sort(Comparator.comparing(Transaction::getDate).reversed());
        statement.sort(Comparator.comparing(Transaction::getTime).reversed());

        for(Transaction transaction: statement){
            System.out.println(transaction); //WIP printf
        }
    }

    private static void handleDeposits() {

        statement.sort(Comparator.comparing(Transaction::getDate).reversed());
        statement.sort(Comparator.comparing(Transaction::getTime).reversed());

        for(Transaction transaction: statement){
            if(transaction.getAmount() > 0){
                System.out.println(transaction); //WIP printf
            }

        }
    }

    private static void handlePayments() {

        statement.sort(Comparator.comparing(Transaction::getDate).reversed());
        statement.sort(Comparator.comparing(Transaction::getTime).reversed());

        for(Transaction transaction: statement){
            if(transaction.getAmount() < 0){
                System.out.println(transaction); //WIP printf
            }

        }
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

            System.out.print("What would you like to do? ");
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
                    System.out.println("Going Back to Ledger");
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