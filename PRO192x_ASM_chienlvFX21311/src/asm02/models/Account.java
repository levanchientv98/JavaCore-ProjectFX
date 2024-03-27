package asm02.models;

import java.text.NumberFormat;
import java.util.Locale;

public class Account {
    private String accountNumber;
    private double balance;

    //    Constructor Account
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
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
        return getAccountNumber() + "\t\t\t\t\t" + str1;
    }

}
