package asm04;

import asm04.model.*;

import java.util.*;

import java.io.*;

import static asm02.Asm02.printCont;
import static asm04.common.BinaryFileService.*;
import static asm04.dao.CustomerDao.listCustomer;

public class Asm04 {

    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "004215000001";
    private static final String CUSTOMER_NAME = "FUNIX";
    private static final String FILE_PATH = "C:/Data-D/Java-Project/PRO192x_ASM_chienlvFX21311/src/asm04/store/list-customer.txt";
    private static final String FILE_PATH_DATA_CUSTOMER = "C:/Data-D/Java-Project/PRO192x_ASM_chienlvFX21311/src/asm04/store/customers.txt";


    // main
    public static void main(String[] args) {
        showCustomer();
//        activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
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
        printCont("NGAN HANG SO | FX21311@v3.0.0", 20, height);
        menuFunction();
    }

    // Show menu Function
    public static void menuFunction() {
        // Print middle border
        System.out.print("+");
        for (int i = 0; i < 65 - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
        // Print middle rows
        System.out.println(" 1. Xem danh sách khách hàng");
        System.out.println(" 2. Nhập danh sách khách hàng");
        System.out.println(" 3. Thêm tài khoản ATM");
        System.out.println(" 4. Chuyển tiền");
        System.out.println(" 5. Rút tiền");
        System.out.println(" 6. Tra cứu lịch sử giao dịch");
        System.out.println(" 0. Thoát");
        // Print bottom border
        System.out.print("+");
        for (int i = 0; i < 65 - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
    //

    // Funcition 1 View customer information
    private static void showCustomer() {
        for (Customer customerInfo : listCustomer()) {
            if (!activeBank.checkCustomerExisted(customerInfo.getCustomerId())) {
                activeBank.addCustomer(customerInfo.getCustomerId(), customerInfo.getName());
            }
            customerInfo = activeBank.getCustomerById(customerInfo.getCustomerId());
            if (customerInfo != null) {
                customerInfo.displayInformation();
            } else {
                System.out.println("Ko tìm thấy khách hàng");
            }
        }
    }

    private static void showCustomerById(Customer customerInfo) {
        Customer customer = activeBank.getCustomerById(customerInfo.getCustomerId());
        if (customer != null) {
            customer.displayInformation();
        } else {
            System.out.println("Ko tìm thấy khách hàng");
        }

    }

    //   Check balance condition
    public static String checkCodeAccount(String codeAccount, String customerID) {
        boolean isAccountEx = activeBank.isAccountEx(codeAccount);
        if (!isAccountEx) {
            activeBank.addListAccount(codeAccount);
        }
        boolean isExistedAccount = activeBank.getCustomerById(customerID).isAccountExisted(codeAccount);
        while (isExistedAccount || !codeAccount.matches("[0-9]{6}") || isAccountEx) {
            while (!codeAccount.matches("[0-9]{6}")) {
                System.out.print("Mã STK gồm 6 chữ số 0 -> 9.\nVui lòng nhập lại đúng định dạng: ");
                codeAccount = scanner.next();
                boolean isExistedAccount1 = activeBank.getCustomerById(customerID).isAccountExisted(codeAccount);
                while (isExistedAccount1) {
                    System.out.print("Mã STK đã tồn tại.\nXin vui lòng nhập lại mã STK gồm 6 chữ số: ");
                    codeAccount = scanner.next();
                    isExistedAccount1 = activeBank.getCustomerById(customerID).isAccountExisted(codeAccount);
                }
            }
            while (isExistedAccount || isAccountEx) {
                System.out.print("Mã STK đã tồn tại.\nXin vui lòng nhập lại mã STK gồm 6 chữ số: ");
                codeAccount = scanner.next();
                isExistedAccount = activeBank.getCustomerById(customerID).isAccountExisted(codeAccount);
                isAccountEx = activeBank.isAccountEx(codeAccount);
            }
        }
        return codeAccount;
    }

    // Function 2 Add new customers
    private static void addNewCustomer() {
//        System.out.println("Nhập đường dẫn đên tệp: ");
//        String filePath = scanner.next();
        List<Customer> customerDataNew = new ArrayList<>();
        List<Customer> customerDataOld = new ArrayList<>();
        String filePath = FILE_PATH_DATA_CUSTOMER;
        customerDataNew = readListCustomer(filePath);
//        customerDataOld = readListCustomer(FILE_PATH);
        if (customerDataOld.size() == 0) {
            writeFileCustomer(FILE_PATH, customerDataNew);
        } else {
            Customer customerInfoNew;
            Customer customerInfoOld;
            for (int i = 0; i < customerDataNew.size(); i++) {
                customerInfoNew = customerDataNew.get(i);
                if (!checkCustomer(customerInfoNew)) {
                    customerDataOld.add(customerInfoNew);
                    writeFileCustomer(FILE_PATH, customerDataOld);
                    System.out.println("Đã thêm khách hàng " + customerInfoNew.getCustomerId() + " vào danh sách khách hàng thành công");
                }

            }
        }

    }

    // Function check isCustomer
    public static boolean checkCustomer(Customer customerInfoNew) {
        boolean isCustomer = false;
        List<Customer> customerDataOld = new ArrayList<>();
        customerDataOld = readListCustomer(FILE_PATH);
        for (Customer customer1 : customerDataOld) {
            if (customerInfoNew.getCustomerId().equals(customer1.getCustomerId())) {
                System.out.println("Khách hàng " + customerInfoNew.getCustomerId() + " đã tồn tại, thêm khách hàng không thành công");
                isCustomer = true;
                break;
            } else {
                isCustomer = false;
            }
        }

        return isCustomer;
    }


    // Function 3 Add an ATM account
    private static void addSavingAccount() {
        String customerID;
        String accountNumber;
        String balance;
        ArrayList<String> listcustomerID = new ArrayList<>();
        ArrayList<String> listAccount = new ArrayList<>();

        System.out.println("Nhập mã số khách hàng: ");
        customerID = scanner.next();

        for (Customer customerInfo : readListCustomer(FILE_PATH)) {
            listcustomerID.add(customerInfo.getCustomerId());
        }

        int index = listcustomerID.indexOf(customerID);
        if (index != -1) {
            System.out.print("Nhập mã STK gồm 6 chữ số: ");
            accountNumber = scanner.next();
            accountNumber = checkCodeAccount(accountNumber, customerID);
            System.out.print("Nhập số dư tài khoản >= 50000đ: ");
            balance = scanner.next();
            double money = checkMoney(balance);
            while (money < 50000) {
                System.out.print("Nhập số dư tài khoản >= 50000đ: ");
                balance = scanner.next();
                money = checkMoney(balance);
            }

            activeBank.addNewAccount(customerID, new SavingsAccount(accountNumber, money));
            System.out.println("Da them STK " + accountNumber);
            activeBank.getCustomerById(customerID).getAccountByNumber(accountNumber).addTransaction(accountNumber, +money, true,"DEPOSIT");

            System.out.println("Thêm tài khoản thành công");
        } else {
            System.out.println("Không tìm thấy khách hàng " + customerID + ", tác vụ không thành công");
            menuFunction();
            inputFunciton();
        }
    }

//    // Funcition 3 Add a signal account
//    private static void addLoanAccount() {
//        String accountNumber;
//        String balance;
//        System.out.print("Nhập mã STK gồm 6 chữ số: ");
//        accountNumber = scanner.next();
////        accountNumber = checkCodeAccount(accountNumber, customerID);
//
//        System.out.print("Nhập số dư tài khoản >= 50000đ: ");
//        balance = scanner.next();
//        double money = checkMoney(balance);
//        while (money < 50000) {
//            System.out.print("Số dư không đủ. Vui lòng nhập lại: ");
//            balance = scanner.next();
//            money = checkMoney(balance);
//        }
//
//        activeBank.addNewAccount(CUSTOMER_ID, new LoanAccount(accountNumber, money));
//        System.out.println("Da them STK " + accountNumber);
//        activeBank.getCustomerById(CUSTOMER_ID).getAccountByNumber(accountNumber).addTransaction(accountNumber, +money, true);
//
//        System.out.println("Thêm tài khoản thành công");
//    }

    //Check the account included in the customer's account list
    private static String checkAccountExistence(String accountNumber, String customerId) {
        Customer customer = activeBank.getCustomerById(customerId);
        while (!(accountNumber.matches("[0-9]{6}") && customer.getAccountByNumber(accountNumber) != null)) {
            if (customer.getAccountByNumber(accountNumber) == null) {
                System.out.print("Số tài khoản không tồn tại trong danh sách tài khoản.\nVui lòng nhập lại hoặc nhấn '0' để thoát: ");
            } else if (!accountNumber.matches("[0-9]{6}")) {
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
    private static double checkMoney(String money) {
        boolean isNumber = true;
        double amount = 0;
        while (isNumber) {
            if (money.matches("[0-9]+")) {
                amount = Double.parseDouble(money);
                isNumber = false;
            } else {
                System.out.print("Số tiền không đúng định dạng. Vui lòng nhập lại: ");
                money = scanner.next();
            }
        }
        return amount;
    }

    //Function 4: Money transfer function
    private static void moneyTransfer() {
        String customerID;
        String accountNumber;
        String reAccountNumber;
        String transferAmount;
        String authentication;
        System.out.println("Nhập mã số khách hàng: ");
        customerID = scanner.next();

        if (isCustomer(customerID)) {
            for (Customer customerInfo1 : listCustomer()) {
                if (customerInfo1.getCustomerId().equals(customerID)) {
                    showCustomerById(customerInfo1);
                }
            }
            System.out.printf("Nhập số tài khoản: ");
            accountNumber = scanner.next();
            accountNumber = checkAccountExistence(accountNumber, customerID);
            System.out.printf("Nhập số tài khoản hoặc nhấn exit để thoát): ");
            reAccountNumber = scanner.next();
            boolean isAccountEx = activeBank.isAccountEx(reAccountNumber);
            while (!isAccountEx) {
                System.out.printf("Nhập số tài khoản hoặc nhấn exit để thoát): ");
                reAccountNumber = scanner.next();
                isAccountEx = activeBank.isAccountEx(reAccountNumber);
                if (reAccountNumber.equals("exit")) {
                    menuFunction();
                    inputFunciton();
                }
            }
//            reAccountNumber = checkAccountExistence(reAccountNumber);

            System.out.printf("Nhập số tiền chuyển: ");
            transferAmount = scanner.next();
            double money = checkMoney(transferAmount);
            System.out.printf("Xác nhận thực hiện chuyển " +String.valueOf( money )+ "đ từ tài khoản [" + accountNumber + "] đến tài khoản [" + reAccountNumber + "] (Y/N): ");
            authentication = scanner.next();
            if (authentication.equalsIgnoreCase("Y")) {
                System.out.println("Chuyển tiền thành công, biên lại giao dịch:");
                activeBank.withdraw(customerID, accountNumber, money, reAccountNumber);
                String reCustomerId = activeBank.findCustomerByAccountId(reAccountNumber);
                activeBank.transfers(reCustomerId, reAccountNumber, money);
            } else {
                System.out.println("Hủy giao dịch quay trở về menu chức năng:");
                menuFunction();
                inputFunciton();
            }

        } else {
            System.out.println("Không tìm thấy khách hàng " + customerID + ", tác vụ không thành công");
            menuFunction();
            inputFunciton();
        }

    }


    //    Function 5: Withdraw money
    private static void withdraw() {
        String customerID;
        String accountNumber;

        System.out.println("Nhập mã số khách hàng: ");
        customerID = scanner.next();
        if (isCustomer(customerID)) {
            for (Customer customerInfo1 : listCustomer()) {
                if (customerInfo1.getCustomerId().equals(customerID)) {
                    showCustomerById(customerInfo1);
                }
            }
            System.out.print("Nhập mã STK gồm 6 chữ số: ");
            accountNumber = scanner.next();
            accountNumber = checkAccountExistence(accountNumber, customerID);

            String amountString;
            System.out.print("Nhập số tiền muốn rút: ");
            amountString = scanner.next();
            double amount = checkMoney(amountString);
            activeBank.withdrawATM(customerID, accountNumber, amount);
        } else {
            System.out.println("Không tìm thấy khách hàng " + customerID + ", tác vụ không thành công");
            menuFunction();
            inputFunciton();
        }
    }

    //Function 6: Look up transaction history
    public static void showTransactions() {
        String customerID;
        System.out.println("Nhập mã số khách hàng: ");
        customerID = scanner.next();
        if(isCustomer(customerID)){
            Customer customer = activeBank.getCustomerById(customerID);
            customer.displayInformation();

            for (Account account : customer.getAccounts()) {
                account.transactionInfo();
            }
        }else {
            System.out.println("Không tìm thấy khách hàng " + customerID + ", tác vụ không thành công");
            menuFunction();
            inputFunciton();
        }
    }

    // Function check isExistCustomer in list customer
    public static boolean isCustomer(String customerID) {
        ArrayList<String> listcustomerID = new ArrayList<>();
        for (Customer customerInfo : readListCustomer(FILE_PATH)) {
            listcustomerID.add(customerInfo.getCustomerId());
        }
        int index = listcustomerID.indexOf(customerID);
        if (index != -1) {
            return true;
        } else {
            return false;
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
                    addNewCustomer();
                    menuFunction();
                    inputFunciton();
                }
                case "3" -> {
                    addSavingAccount();
                    menuFunction();
                    inputFunciton();
                }
                case "4" -> {
                    moneyTransfer();
                    menuFunction();
                    inputFunciton();
                }
                case "5" -> {
                    withdraw();
                    menuFunction();
                    inputFunciton();
                }
                case "6" -> {
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