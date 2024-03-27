package asm03.models;

import asm03.Utils;
import asm03.interfaces.ReportService;
import asm03.interfaces.Withdraw;

import java.text.NumberFormat;
import java.util.Locale;

public class LoanAccount extends Account implements Withdraw, ReportService {

    private final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private final double LOAN_ACCOUNT_MAX_BALANCE = 1000000000;

    public LoanAccount(String accountNumber, double balance) {
        super(accountNumber, Math.min(balance, 1000000000));
    }

    @Override
    public String toString() {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(getBalance());
        return super.getAccountNumber() + " | \t"+"LOAN \t|\t\t\t\t" + str1;
    }

    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n",Utils.getTitle()+" LOAN");
        System.out.printf("NGAY G/D: %28s%n",Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n","DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n",getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(getTransactionFee()*amount));
    }

    public double getTransactionFee(){
        if(!isPremium()){
            return LOAN_ACCOUNT_WITHDRAW_FEE;
        }else {
            return LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE;
        }
    }

    @Override
    public boolean withdraw(double amount) {
        double newBalance;
        if(isAccepted(amount)){
            newBalance = getBalance()-(amount+(amount*getTransactionFee()));
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
        if (amount <= LOAN_ACCOUNT_MAX_BALANCE && minBalance(getBalance()-amount)){
            return true;
        }else {
            if(amount > LOAN_ACCOUNT_MAX_BALANCE){
                System.out.println("So tien rut khong the vuot qua han muc. Han muc hien tai: "+Utils.formatBalance(LOAN_ACCOUNT_MAX_BALANCE));
            }
            if(!minBalance(getBalance()-amount)){
                System.out.println("So du sau khi rut duoi 50,000. Vui long thu lai sau. ");
            }
        }
        return false;
    }
}
