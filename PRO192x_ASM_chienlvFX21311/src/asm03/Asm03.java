package asm03;

import asm03.models.*;

import java.util.Scanner;

import static asm02.Asm02.printCont;

public class Asm03 {

    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "004215000001";
    private static final String CUSTOMER_NAME = "FUNIX";

    // main
    public static void main(String[] args) {
        activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
        printBox(65, 3);
        inputFunciton();
    }

    //    Create border for the title
    public static void printBox(int width, int height) {
        // Print top border
        System.out.print("+");
        for (int i = 0; i < width - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");

        // Print middle rows
        printCont("NGAN HANG SO | FX21311@v3.0.0", width, height);
        menuFunction();
    }

    // Show menu Function
    public static void menuFunction() {
        // Print middle border
        System.out.print("+");
        for (int i = 0; i < 50 - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
        // Print middle rows
        System.out.println(" 1. Xem Thông tin khách hàng");
        System.out.println(" 2. Thêm tài khoản ATM");
        System.out.println(" 3. Thêm tài khoản tín dụng");
        System.out.println(" 4. Rút tiền");
        System.out.println(" 5. Lịch sử giao dịch");
        System.out.println(" 0. Thoát");
        // Print bottom border
        System.out.print("+");
        for (int i = 0; i < 50 - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

    // Funcition 1 View customer information
    private static void showCustomer() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer != null) {
            customer.displayInformation();
        }
    }

    //   Check balance condition
    public static String checkCodeAccount(String codeAccount) {
        boolean isExistedAccount = activeBank.getCustomerById(CUSTOMER_ID).isAccountExisted(codeAccount);
        while (isExistedAccount || !codeAccount.matches("[0-9]{6}")) {
            while (!codeAccount.matches("[0-9]{6}")) {
                System.out.print("Mã STK gồm 6 chữ số 0 -> 9.\nVui lòng nhập lại đúng định dạng: ");
                codeAccount = scanner.next();
                boolean isExistedAccount1 = activeBank.getCustomerById(CUSTOMER_ID).isAccountExisted(codeAccount);
                while (isExistedAccount1) {
                    System.out.print("Mã STK đã tồn tại.\nXin vui lòng nhập lại mã STK gồm 6 chữ số: ");
                    codeAccount = scanner.next();
                    isExistedAccount1 = activeBank.getCustomerById(CUSTOMER_ID).isAccountExisted(codeAccount);
                }
            }
            while (isExistedAccount) {
                System.out.print("Mã STK đã tồn tại.\nXin vui lòng nhập lại mã STK gồm 6 chữ số: ");
                codeAccount = scanner.next();
                isExistedAccount = activeBank.getCustomerById(CUSTOMER_ID).isAccountExisted(codeAccount);
            }
        }
        return codeAccount;
    }

    // Function 2 Add an ATM account
    private static void addSavingAccount() {
        String accountNumber;
        String balance;
        System.out.print("Nhập mã STK gồm 6 chữ số: ");
        accountNumber = scanner.next();
        accountNumber = checkCodeAccount(accountNumber);

        System.out.print("Nhập số dư: ");
        balance = scanner.next();
        double money = checkMoney(balance);
        while (money < 50000) {
            System.out.print("Số dư không đủ. Vui lòng nhập lại: ");
            balance = scanner.next();
            money = checkMoney(balance);
        }

        activeBank.addNewAccount(CUSTOMER_ID, new SavingsAccount(accountNumber, money));
        System.out.println("Da them STK " + accountNumber);
        activeBank.getCustomerById(CUSTOMER_ID).getAccountByNumber(accountNumber).addTransaction(accountNumber, +money, true);

        System.out.println("Thêm tài khoản thành công");
    }

    // Funcition 3 Add a signal account
    private static void addLoanAccount() {
        String accountNumber;
        String balance;
        System.out.print("Nhập mã STK gồm 6 chữ số: ");
        accountNumber = scanner.next();
        accountNumber = checkCodeAccount(accountNumber);

        System.out.print("Nhập số dư: ");
        balance = scanner.next();
        double money = checkMoney(balance);
        while (money < 50000) {
            System.out.print("Số dư không đủ. Vui lòng nhập lại: ");
            balance = scanner.next();
            money =checkMoney(balance);
        }

        activeBank.addNewAccount(CUSTOMER_ID, new LoanAccount(accountNumber, money));
        System.out.println("Da them STK " + accountNumber);
        activeBank.getCustomerById(CUSTOMER_ID).getAccountByNumber(accountNumber).addTransaction(accountNumber, +money, true);

        System.out.println("Thêm tài khoản thành công");
    }

    //Check the account included in the customer's account list
    private static String checkAccountExistence(String accountNumber){
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        while (!(accountNumber.matches("[0-9]{6}") && customer.getAccountByNumber(accountNumber) !=null)){
            if(customer.getAccountByNumber(accountNumber) == null){
                System.out.print("Số tài khoản không tồn tại trong danh sách tài khoản.\nVui lòng nhập lại hoặc nhấn '0' để thoát: ");
            }
            else if (!accountNumber.matches("[0-9]{6}")){
                System.out.print("Số tài khoản không đúng định dạng.\nVui lòng nhập lại hoặc nhấn '0' để thoát: ");
            }
            accountNumber = scanner.next();
            if (accountNumber.equals(String.valueOf(EXIT_COMMAND_CODE))) {
                System.exit(0);
            }
        }
        return accountNumber;
    }

    //Check invalid enter money
    private static double checkMoney(String money){
        boolean isNumber = true;
        double amount = 0;
        while (isNumber){
            if(money.matches("[0-9]+")){
                amount = Double.parseDouble(money);
                isNumber = false;
            }else {
                System.out.print("Số tiền không đúng định dạng. Vui lòng nhập lại: ");
                money = scanner.next();
            }
        }
        return amount;
    }

    //Function 4: Withdraw money
    private static void withdraw(){
        String accountNumber;
        
        System.out.print("Nhập mã STK gồm 6 chữ số: ");
        accountNumber = scanner.next();
        accountNumber = checkAccountExistence(accountNumber);

        String amountString;
        System.out.print("Nhập số tiền muốn rút: ");
        amountString = scanner.next();
        double amount =  checkMoney(amountString);
        activeBank.withdraw(CUSTOMER_ID,accountNumber,amount);
    }

    //Function 5: Look up transaction history
    public static void showTransactions(){
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        customer.displayInformation();

        for (Account account: customer.getAccounts() ){
            account.transactionInfo();
        }
    }


    // create function input function
    public static void inputFunciton() {
        String numberFunciton;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chức năng: ");
        numberFunciton = scanner.nextLine();
        boolean flag = true;
        while (flag) {
            switch (numberFunciton) {
                case "0" -> System.exit(0);
                case "1" -> {
                    showCustomer();
                    menuFunction();
                    inputFunciton();
                }
                case "2" -> {
                    addSavingAccount();
                    menuFunction();
                    inputFunciton();
                }
                case "3" -> {
                    addLoanAccount();
                    menuFunction();
                    inputFunciton();
                }
                case "4" -> {
                    withdraw();
                    menuFunction();
                    inputFunciton();
                }
                case "5" -> {
                    showTransactions();
                    menuFunction();
                    inputFunciton();
                }
                default -> {
                    System.out.println("Chọn lại chức năng trong khoảng 0 -> 5 ");
                    inputFunciton();
                    flag = false;
                }
            }
        }
    }


}
