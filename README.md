# Capstone 1: Accounting Ledger Application
***

## Description:
My code is a CLI application that consists of three parts; 
a main class, a transaction class, and a csv file named transactions.

The transaction class is made up of properties, a constructor, and various getter-setter methods. At the very end there is a to-string method.

The csv file is where all the banking information is being held. The main class will
allow for the text to be read through and convert into instance variables of the 
transaction class. Users will be able to input information into the csv file, adding
to the list as many times as they please. The csv file allows the information to be
saved even after the user exits the application and will return if used again.

The main class is where all the code that makes up the application lies. It consists
of the main method (the only place for the code to run) and many other methods that 
I have created to help keep organized and easy to read.

## Intended outcome of the Application:
***

![image of home](/screenshots/home.PNG)

![deposit_Image_Y_Yes](/screenshots/deposit_Y_Yes.PNG)

![deposit_Image_Y_No](/screenshots/deposit_Y_No.PNG) 

![deposit_Image_N](/screenshots/deposit_N.PNG) 

![payment_Image_Y_Yes](/screenshots/payment_Y_Yes.PNG) 

![payment_Image_Y_No](/screenshots/payment_Y_No.PNG)

![payment_Image_N](/screenshots/payment_N.PNG) 

![ledger menu image](/screenshots/Ledger_Menu.PNG) 

![exit image](/screenshots/exit_Application.PNG)

![all image](/screenshots/all_Ledger.PNG)

![deposit image](/screenshots/deposit_Ledger.PNG) 

![payment image](/screenshots/payment_Ledger.PNG)

![report menu image](/screenshots/report_Menu.PNG)

![back to home image](/screenshots/back_to_Home.PNG)

![month to date image](/screenshots/Mo_to_Date.PNG)

![previous month image](/screenshots/previous_Mo.PNG)

![year to date image](/screenshots/year_to_Date.PNG)

![previous year image](/screenshots/previous_Year.PNG)

![search vendor type & results image](/screenshots/vendor_Report.PNG)

![custom search start date image](/screenshots/custom_Start_Date.PNG) 

![custom search end date image](/screenshots/custom_Start_End_Date.PNG)

![custom search start, end , and description](/screenshots/custom_Start_End_Des.PNG)

![custom search amount image ](/screenshots/custom_Amount.PNG)

![back to ledger image](/screenshots/back_to_Ledger.PNG)

## Interesting Code:
***

All the code that I find interesting from this project are the lines of code 
where I learned something new.

I have learned that you can make your own comparator to sort the values in your array.
For this project we had to sort the values based on newest to oldest entries. Because 
every new entry is the most recent time and date for my application, I had to make 
a comparator to compare each transaction to both date and time.

Another thing that I learned, was how to further use printf to format the text. 
I can use the "-" character to make the text left-aligned and assign a number of 
characters that can fit into that space.
```java
public static void sortAndFormat(){

    bankStatement.sort(Comparator.comparing(Transaction::getDate)
            .thenComparing(Transaction::getTime).reversed());

    System.out.printf("\n%-15s %-15s %-25s %-15s %-10s%n", "Date", "Time", "Description","Vendor","Amount");
    System.out.println("----------------------------------------------------------------------------------");
}

```

I also learned a lot from this line of code too. I learned that you cannot compare
a null value to another value, and to combat this issue I had to assign an unused
value to the variable. This only happens when the user chooses to leave a field blank.
I also learned about the .isEqual(), .isBefore(), and .isAfter() methods used for dates.
```java
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

        for(Transaction transaction: bankStatement){

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

```










