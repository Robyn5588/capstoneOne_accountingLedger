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
    static Scanner scannerDigit = new Scanner(System.in);
    static Scanner scannerLine = new Scanner(System.in);
    static ArrayList<Transaction> statement = new ArrayList<>();
    static LocalDate dateStamp = LocalDate.now();
    static LocalTime timeStamp = LocalTime.now();

    public static void main(String[] args) {

        loadStatement();
        int mainMenuCommand;

        do{
            System.out.println("\nAccounting Ledger Application Home");
            System.out.println("1) Add Deposit");
            System.out.println("2) Make Payment ");
            System.out.println("3) Ledger");
            System.out.println("0) Exit ");

            System.out.print("What would you like to do? ");
            mainMenuCommand = scannerDigit.nextInt();

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

    public static void sortAndFormat(){

        statement.sort(Comparator.comparing(Transaction::getDate)
                .thenComparing(Transaction::getTime).reversed());

        System.out.printf("\n%-15s %-15s %-25s %-15s %-10s%n", "Date", "Time", "Description","Vendor","Amount");
        System.out.println("----------------------------------------------------------------------------------");
    }

    private static void loadStatement(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));

            String input;

            while ((input = bufferedReader.readLine()) != null){
                String[] fields = input.split("\\|");
                LocalDate date = LocalDate.parse(fields[0]);
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

        String addDepositCommand;
        String confirmationInput;

        do{
            System.out.print("\nWould you like to make a Deposit? (Y/N) ");
            addDepositCommand = scannerLine.nextLine().toUpperCase();

            switch (addDepositCommand.trim()){
                case "Y":
                    System.out.print("\nEnter Description: ");
                    String writeDescription = scannerLine.nextLine().trim();

                    System.out.print("Enter Vendor: ");
                    String writeVendor = scannerLine.nextLine().trim();

                    System.out.print("Enter Amount: ");
                    double writeAmount = scannerDigit.nextDouble();
                    writeAmount = Math.abs(writeAmount);

                    sortAndFormat();
                    System.out.printf("%-15tF %-15tT %-25s %-15s %-10.2f%n",dateStamp,timeStamp, writeDescription,writeVendor, writeAmount);

                    System.out.print("\nIs the Information Above Correct? (Yes/No) ");
                    confirmationInput = scannerLine.nextLine().trim();
                    if(confirmationInput.equalsIgnoreCase("yes")){
                        try{
                            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true)); // Explain

                            String formattedTransaction = String.format("%tF|%tT|%s|%s|%.2f\n"
                                    ,dateStamp,timeStamp, writeDescription,writeVendor, writeAmount);
                            bufferedWriter.write(formattedTransaction);

                            Transaction transaction = new Transaction(dateStamp,timeStamp, writeDescription,writeVendor, writeAmount);
                            statement.add(transaction);

                            bufferedWriter.close();
                        }catch (Exception e){
                            throw new RuntimeException(e);
                        }
                    } else if (confirmationInput.equalsIgnoreCase("no")) {
                        System.out.println("Please Try Again");

                    }else{
                        System.out.println("Invalid Input, Try again");
                    }
                    break;
                case "N":
                    System.out.println("Going Back to home");
                    break;
                default:
                    System.out.println("Invalid option, Try Again");
            }
        } while (!addDepositCommand.equalsIgnoreCase("N"));
    }

    private static void handleMakePayment() {

        String makePaymentCommand;
        String confirmationInput;

        do{
            System.out.print("\nWould you like to make a Payment? (Y/N) ");
            makePaymentCommand = scannerLine.nextLine().toUpperCase();

            switch (makePaymentCommand.trim()){
                case "Y":
                    System.out.print("\nEnter Description: ");
                    String writeDescription = scannerLine.nextLine().trim();

                    System.out.print("Enter Vendor: ");
                    String writeVendor = scannerLine.nextLine().trim();

                    System.out.print("Enter Amount: ");
                    double writeAmount = scannerDigit.nextDouble();
                    writeAmount = -Math.abs(writeAmount);

                    sortAndFormat();
                    System.out.printf("%-15tF %-15tT %-25s %-15s %-10.2f%n",dateStamp,timeStamp, writeDescription,writeVendor, writeAmount);

                    System.out.print("\nIs the Information Above Correct? (Yes/No) ");
                    confirmationInput = scannerLine.nextLine().trim();
                    if(confirmationInput.equalsIgnoreCase("yes")){
                        try{
                            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));

                            String formattedTransaction = String.format("%tF|%tT|%s|%s|%.2f\n"
                                    ,dateStamp,timeStamp, writeDescription,writeVendor, writeAmount);
                            bufferedWriter.write(formattedTransaction);

                            Transaction transaction = new Transaction(dateStamp,timeStamp, writeDescription,writeVendor, writeAmount);
                            statement.add(transaction);

                            bufferedWriter.close();
                        }catch (Exception e){
                            throw new RuntimeException(e);
                        }
                    } else if (confirmationInput.equalsIgnoreCase("no")) {
                        System.out.println("Please Try Again");

                    }else{
                        System.out.println("Invalid Input, Try again");
                    }
                    break;
                case "N":
                    System.out.println("Going Back to home");
                    break;
                default:
                    System.out.println("Invalid option, Try Again");
            }
        } while (!makePaymentCommand.equalsIgnoreCase("N"));
    }

    private static void handleLedger() {

        int ledgerMenuCommand;
        do{
            System.out.println("\nLedger Menu");
            System.out.println("1) All");
            System.out.println("2) Deposits ");
            System.out.println("3) Payments");
            System.out.println("4) Reports");
            System.out.println("0) Back to Home ");

            System.out.print("What would you like to do? ");
            ledgerMenuCommand = scannerDigit.nextInt();

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

        sortAndFormat();

        for(Transaction transaction: statement){
            System.out.printf("%-15tF %-15tT %-25s %-15s %-10.2f%n"
                    ,transaction.getDate(),transaction.getTime(),transaction.getDescription(),transaction.getVendor(),transaction.getAmount());
        }
    }

    private static void handleDeposits() {

        sortAndFormat();

        for(Transaction transaction: statement){
            if(transaction.getAmount() > 0){
                System.out.printf("%-15tF %-15tT %-25s %-15s %-10.2f%n"
                        ,transaction.getDate(),transaction.getTime(),transaction.getDescription(),transaction.getVendor(),transaction.getAmount());
            }
        }
    }

    private static void handlePayments() {

        sortAndFormat();

        for(Transaction transaction: statement){
            if(transaction.getAmount() < 0){
                System.out.printf("%-15tF %-15tT %-25s %-15s %-10.2f%n"
                        ,transaction.getDate(),transaction.getTime(),transaction.getDescription(),transaction.getVendor(),transaction.getAmount());
            }
        }
    }

    private static void handleReports() {

        int reportsMenuCommand;
        do{
            System.out.println("\nReports Menu");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back to Ledger ");

            System.out.print("What would you like to do? ");
            reportsMenuCommand = scannerDigit.nextInt();

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
                case 6:
                    handleCustomSearch();
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

        sortAndFormat();

        for(Transaction transaction: statement){
            if(dateStamp.getYear() == transaction.getDate().getYear()){
                if(dateStamp.getMonth().equals(transaction.getDate().getMonth())){
                    System.out.printf("%-15tF %-15tT %-25s %-15s %-10.2f%n"
                            ,transaction.getDate(),transaction.getTime(),transaction.getDescription(),transaction.getVendor(),transaction.getAmount());
                }
            }
        }
    }

    private static void handlePreviousMonth() {

        sortAndFormat();

        LocalDate previousMonth = dateStamp.minusMonths(1);

        for(Transaction transaction: statement){
            if(dateStamp.getYear() == transaction.getDate().getYear()){
                if(previousMonth.getMonth().equals(transaction.getDate().getMonth())){
                    System.out.printf("%-15tF %-15tT %-25s %-15s %-10.2f%n"
                            ,transaction.getDate(),transaction.getTime(),transaction.getDescription(),transaction.getVendor(),transaction.getAmount());
                }
            }
        }
    }

    private static void handleYearToDate() {

        sortAndFormat();

        for(Transaction transaction: statement){
            if(dateStamp.getYear() == transaction.getDate().getYear()){
                System.out.printf("%-15tF %-15tT %-25s %-15s %-10.2f%n"
                        ,transaction.getDate(),transaction.getTime(),transaction.getDescription(),transaction.getVendor(),transaction.getAmount());
            }
        }
    }

    private static void handlePreviousYear() {

        sortAndFormat();

        LocalDate previousYear = dateStamp.minusYears(1);

        for(Transaction transaction: statement){
            if(previousYear.getYear() == transaction.getDate().getYear()){
                System.out.printf("%-15tF %-15tT %-25s %-15s %-10.2f%n"
                        ,transaction.getDate(),transaction.getTime(),transaction.getDescription(),transaction.getVendor(),transaction.getAmount());
            }
        }
    }

    private static void handleSearchByVendor() {

        System.out.print("\nPlease enter Vendor Name: ");
        String vendorInput = scannerLine.nextLine().trim();

        sortAndFormat();

        for(Transaction transaction: statement){
            if(vendorInput.equalsIgnoreCase(transaction.getVendor())){
                System.out.printf("%-15tF %-15tT %-25s %-15s %-10.2f%n"
                        ,transaction.getDate(),transaction.getTime(),transaction.getDescription(),transaction.getVendor(),transaction.getAmount());
            }
        }
    }

    private static void handleCustomSearch() {

        System.out.print("\nPlease enter Start Date (YYYY-MM-DD): ");
        String startDateInput = scannerLine.nextLine().trim();

        System.out.print("Please enter End Date (YYYY-MM-DD): ");
        String endDateInput = scannerLine.nextLine().trim();

        System.out.print("Please enter Description: ");
        String descriptionInput = scannerLine.nextLine().trim();

        System.out.print("Please enter Vendor: ");
        String vendorInput = scannerLine.nextLine().trim();

        System.out.print("Please enter Amount: ");
        String amountInput = scannerLine.nextLine();

        LocalDate startDate;
        if(startDateInput.isEmpty()){
            startDate = LocalDate.parse("1800-01-01");
        }else{
            startDate = LocalDate.parse(startDateInput);
        }

        LocalDate endDate;
        if(endDateInput.isEmpty()){
            endDate = LocalDate.parse("1800-01-01");
        }else{
            endDate = LocalDate.parse(endDateInput);
        }

        double amountInputDouble;
        if(amountInput.isEmpty()){
            amountInputDouble = Double.parseDouble("0");
        }else{
            amountInputDouble = Double.parseDouble(amountInput);
        }


        sortAndFormat();

        ArrayList<Transaction> filter = new ArrayList<>();

        for(Transaction transaction: statement){

            LocalDate transactionDate = transaction.getDate();

            if((transactionDate.isEqual(startDate) || transactionDate.isAfter(startDate))
                    && (transactionDate.isEqual(endDate) || transactionDate.isBefore(endDate))){

                if(descriptionInput.equalsIgnoreCase(transaction.getDescription()) &&
                        vendorInput.equalsIgnoreCase(transaction.getVendor()) && amountInputDouble == transaction.getAmount()){

                    filter.add(transaction);

                }else if (descriptionInput.isEmpty()  && vendorInput.isEmpty() && amountInput.isEmpty()){
                    filter.add(transaction);
                } else if (descriptionInput.equalsIgnoreCase(transaction.getDescription()) ||
                        vendorInput.equalsIgnoreCase(transaction.getVendor()) || amountInputDouble == transaction.getAmount()) {

                    filter.add(transaction);
                }

            }else if (endDateInput.isEmpty() && (transactionDate.isEqual(startDate) || transactionDate.isAfter(startDate))) {

                if(descriptionInput.equalsIgnoreCase(transaction.getDescription()) &&
                        vendorInput.equalsIgnoreCase(transaction.getVendor()) && amountInputDouble == transaction.getAmount()){

                    filter.add(transaction);

                }else if (descriptionInput.isEmpty()  && vendorInput.isEmpty() && amountInput.isEmpty()){
                    filter.add(transaction);
                } else if (descriptionInput.equalsIgnoreCase(transaction.getDescription()) ||
                        vendorInput.equalsIgnoreCase(transaction.getVendor()) || amountInputDouble == transaction.getAmount()) {

                    filter.add(transaction);
                }

            } else if (startDateInput.isEmpty() && (transactionDate.isEqual(endDate) || transactionDate.isBefore(endDate))){

                if(descriptionInput.equalsIgnoreCase(transaction.getDescription()) &&
                        vendorInput.equalsIgnoreCase(transaction.getVendor()) && amountInputDouble == transaction.getAmount()){

                    filter.add(transaction);

                }else if (descriptionInput.isEmpty()  && vendorInput.isEmpty() && amountInput.isEmpty()){
                    filter.add(transaction);
                } else if (descriptionInput.equalsIgnoreCase(transaction.getDescription()) ||
                        vendorInput.equalsIgnoreCase(transaction.getVendor()) || amountInputDouble == transaction.getAmount()) {

                    filter.add(transaction);
                }

            }else if ( ( startDateInput.isEmpty() && endDateInput.isEmpty()) &&
                    (descriptionInput.equalsIgnoreCase(transaction.getDescription() ) ||
                            vendorInput.equalsIgnoreCase(transaction.getVendor()) || amountInputDouble == transaction.getAmount())) {

                filter.add(transaction);
            }
        }

        for (Transaction transaction : filter) {

            System.out.printf("%-15tF %-15tT %-25s %-15s %-10.2f%n"
                    ,transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

        }

    }

}