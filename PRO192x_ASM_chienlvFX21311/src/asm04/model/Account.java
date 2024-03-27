package asm04.model;

import asm04.Utils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Account {
    private String accountNumber;
    private double balance;
    private final List<Transaction> transactions;


    //    Constructor Account
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    //check premium customer conditions
    public boolean isPremium() {
        if (this.balance >= 10000000) {
            return true;
        }
        return false;
    }

    //-------------------begin getter and setter--------------------
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    //-------------------end getter and setter--------------------

    //Function format balanace
    public String toString() {
        // tạo 1 NumberFormat để định dạng tiền tệ theo tiêu chuẩn của Việt Nam
        // đơn vị tiền tệ của Việt Nam là đồng
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(getBalance());
        return getAccountNumber() + " |\t\t\t\t\t\t\t" + str1;
    }
    // Get list Transaction
    public List<Transaction> getTransactions(){
        return transactions;
    }

    // Add Transaction
    public void addTransaction(String accountNumber, double amount, boolean status, String type){
        this.transactions.add(new Transaction(accountNumber,amount,status,type));
    }

    // Create a minimum balance in an ATM account
    public static boolean minBalance(double balance){
        return balance >= 50000;
    }

    public void transactionInfo(){
        for (Transaction transaction: transactions ){
            System.out.printf("%-6s%-2s | %-10s | %-15s |   %-40s \n","[GD]",accountNumber,transaction.getType(),Utils.formatBalance(transaction.getAmount())+" đ",transaction.getTime());
        }
    }
}
