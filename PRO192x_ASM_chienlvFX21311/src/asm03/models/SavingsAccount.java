package asm03.models;

import asm03.Utils;
import asm03.interfaces.ReportService;
import asm03.interfaces.Withdraw;

import java.text.NumberFormat;
import java.util.Locale;

public class SavingsAccount extends Account implements Withdraw, ReportService {

    private final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public String toString() {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(getBalance());
        return super.getAccountNumber() + " | \t"+"SAVINGS |\t\t\t\t" + str1;
    }

    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", Utils.getTitle()+" SAVING");
        System.out.printf("NGAY G/D: %28s%n",Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n","DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n",getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(0.0));
    }

    @Override
    public boolean withdraw(double amount) {
        double newBalance;
        if(isAccepted(amount)){
            newBalance = getBalance()-amount;
            setBalance(newBalance);
            return true;
        }
        return false;
    }

    @Override
    public boolean transfers(String accountNumber, double amount, String reAccountNumber) {
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        double minAmount = 50000;
        if (minBalance((getBalance()-amount)) && amount%10000==0 && amount>=minAmount){
            if(!isPremium()){
                if(amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW){
                    return true;
                }else {
                    System.out.println("Vì tài khoàn Normal nên không thể rút trên 5,000,000 trên một lần giao dịch.\nVui lòng thử lại sau.");
                }
            }else {
                return true;
            }
        }else {
            if(!minBalance(getBalance() - amount)){
                System.out.println("Số dư sau khi rút dưới 50,000. Vui lòng thử lại sau.");
            }
            else if(amount%10000!=0){
                System.out.println("So tien rut phai la boi cua 10,000. Vui long thu lai sau. ");
            }
            else if (amount<minAmount){
                System.out.println("So tien rut toi thieu 50,000.Vui long thu lai sau.");
            }
        }
        return false;
    }
}
