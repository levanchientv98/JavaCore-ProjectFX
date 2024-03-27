package asm02;

import asm02.models.Account;
import asm02.models.Bank;
import asm02.models.Customer;

import java.util.Scanner;

import static asm01.Asm01.isValidInput;

public class Asm02 {
    // Create new bank
    private static final Bank bank = new Bank();

    //    Create padding for content
    public static void printCont(String content, int width, int height) {
        for (int i = 0; i < height - 2; i++) {
            System.out.print("|");
            if (i == (height - 2) / 2) {
                int padding = width / 2;
                for (int j = 0; j < 2; j++) {
                    System.out.print(" ");
                }
                System.out.print(content);
                for (int j = padding + content.length(); j < 71; j++) {
                    System.out.print(" ");
                }
            } else {
                for (int j = 0; j < width - 2; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println("|");
        }
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
        printCont("NGAN HANG SO | FX21311@v2.0.0", width, height);
        menuFunction();
    }

    public static void menuFunction() {
        // Print middle border
        System.out.print("+");
        for (int i = 0; i < 50 - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
        // Print middle rows
        System.out.println(" 1. Thêm khách hàng");
        System.out.println(" 2. Thêm tài khoản cho khách hàng");
        System.out.println(" 3. Hiển thị danh sách khách hàng");
        System.out.println(" 4. Tìm theo CCCD");
        System.out.println(" 5. Tìm theo tên khách hàng");
        System.out.println(" 0. Thoát");
        // Print bottom border
        System.out.print("+");
        for (int i = 0; i < 50 - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
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
                    addCustomer();
                    menuFunction();
                    inputFunciton();
                }
                case "2" -> {
                    addAccount();
                    menuFunction();
                    inputFunciton();
                }
                case "3" -> {
                    viewCustomerLists();
                    menuFunction();
                    inputFunciton();
                }
                case "4" -> {
                    searchCustomerByCCCD();
                    menuFunction();
                    inputFunciton();
                }
                case "5" -> {
                    searchCustomerByName();
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
    

//      Add customers to the bank use setCustomerId to check
//    public static void addCustomer1() {
//        Scanner sc = new Scanner(System.in);
//        String name;
//        String customeId;
//        System.out.print("Nhập tên khách hàng: ");
//        name = sc.nextLine();
//        System.out.print("Nhập số CCCD: ");
//        customeId = sc.next();
//        try {
//            Customer custome = new Customer(name, customeId, null);
//            custome.setCustomerId(customeId);
//            Customer customer = new Customer(name, customeId, null);
//            bank.addCustomer(customer);
//            System.out.println("Đã thêm khách hàng " + customeId + " vào danh sách");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    // Add customers to the bank
    public static void addCustomer() {
        Scanner sc = new Scanner(System.in);
        String name;
        String customeId;
        System.out.print("Nhập tên khách hàng: ");
        name = sc.nextLine();
        System.out.print("Nhập số CCCD: ");
        customeId = sc.next();
        while (!isValidInput(customeId)) {
            System.out.print("Số CCCD không hợp lệ.\nVui lònng nhập lại hoặc ‘No’ để thoát: ");
            customeId = sc.next();
            if (customeId.equalsIgnoreCase("No")) {
                System.exit(0);
            }
        }
        boolean isExited = bank.isCustomerExisted(customeId);
        while (isExited) {
            System.out.print("Số CCCD đã tồn tại.\nVui lòng nhập lại số CCCD: ");
            customeId = sc.next();
            isExited = bank.isCustomerExisted(customeId);
        }
        Customer customer = new Customer(name, customeId, null);
        bank.addCustomer(customer);
        System.out.println("Đã thêm khách hàng " + customeId + " vào danh sách");
    }

    //Add an account for a customer
    public static void addAccount() {
        Scanner sc = new Scanner(System.in);
        String customeId;
        String accNumber;
        double accBlance;
        System.out.print("Nhập CCCD khách hàng: ");
        customeId = sc.next();
        boolean isExited = bank.isCustomerExisted(customeId);
        while (!isExited) {
            System.out.print("CCCD của khách hàng không tồn tại trong hệ thống.\nXin vui lòng nhập lại CCCD hoặc gõ 'reback' để quay lại menu: ");
            customeId = sc.next();
            if(customeId.equalsIgnoreCase("reback")){
                menuFunction();
                inputFunciton();
            }
            isExited = bank.isCustomerExisted(customeId);
        }

        System.out.print("Nhập mã STK gồm 6 chữ số: ");
        accNumber = sc.next();
        accNumber = checkCodeAccount(accNumber);

        System.out.print("Nhập số dư: ");
        accBlance = sc.nextDouble();
        while (accBlance < 50000) {
            System.out.print("Số dư không đủ. Vui lòng nhập lại: ");
            accBlance = sc.nextDouble();
        }

        Account account = new Account(accNumber, accBlance);

        for (int i = 0; i < bank.getCustomers().size(); i++) {
            boolean isExistedAccount = bank.getCustomers().get(i).isAccountExisted(accNumber);
            if (!isExistedAccount && bank.getCustomers().get(i).getCustomerId().equals(customeId)) {
                bank.getCustomers().get(i).addAccount(account);
            }
        }
        System.out.println("Thêm tài khoản thành công");
    }

    //   Check balance condition
    public static String checkCodeAccount(String codeAccount) {
        Scanner sc = new Scanner(System.in);
        //Check is existed account
        while (!codeAccount.matches("[0-9]{6}")){
            System.out.print("Mã STK gồm 6 chữ số 0 -> 9.\nVui lòng nhập lại đúng định dạng: ");
            codeAccount = sc.next();
        }
        for (int i = 0; i < bank.getCustomers().size(); i++) {
            boolean isExistedAccount = bank.getCustomers().get(i).isAccountExisted(codeAccount);
            while (isExistedAccount || !codeAccount.matches("[0-9]{6}")) {
                System.out.print("Mã STK đã tồn tại hoặc không đúng định dạng.\nXin vui lòng nhập lại mã STK gồm 6 chữ số: ");
                codeAccount = sc.next();
                isExistedAccount = bank.getCustomers().get(i).isAccountExisted(codeAccount);
            }
        }
        return codeAccount;
    }

    // Function Search information by customer CCCD
    public static void searchCustomerByCCCD() {
        Scanner sc = new Scanner(System.in);
        String customeId;
        System.out.print("Nhập CCCD khách hàng: ");
        customeId = sc.next();
        boolean isExited = bank.isCustomerExisted(customeId);
        while (!isExited) {
            System.out.print("CCCD của khách hàng không tồn tại trong hệ thống.\nXin vui lòng nhập lại CCCD: ");
            customeId = sc.next();
            isExited = bank.isCustomerExisted(customeId);
        }
        System.out.println("Thông tin tài khoản khách hàng:");
        for (int i = 0; i < bank.getCustomers().size(); i++) {
            if (bank.getCustomers().get(i).getCustomerId().equals(customeId)) {
                bank.getCustomers().get(i).displayInformation();
            }
        }
    }

    //  Function search information by customer name
    public static void searchCustomerByName() {
        Scanner sc = new Scanner(System.in);
        boolean foundName = false;
        while (!foundName) {
            String keyword;
            System.out.print("Nhập tên khách hàng: ");
            keyword = sc.next();
            String format = keyword.trim().toLowerCase();
            //  get a list of customers
            for (Customer customer : bank.getCustomers()) {
                // Check that the input string is similar to the customer's name
                if (customer.getName().trim().toLowerCase().contains(format)) {
                    System.out.println("Thông tin tài khoản khách hàng:");
                    customer.displayInformation();
                    foundName = true;
                }
            }

            if (!foundName) {
                System.out.println("Tên của khách hàng không tồn tại trong hệ thống. ");
            }
        }
    }

    // Function show list of customers
    public static void viewCustomerLists() {
        for (int i = 0; i < bank.getCustomers().size(); i++) {
            bank.getCustomers().get(i).displayInformation();
        }
    }

    // Main
    public static void main(String[] args) {
        printBox(50, 3);
        inputFunciton();
    }
}
