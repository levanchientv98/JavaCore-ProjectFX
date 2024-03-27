package asm04.model;

import asm04.Utils;
import asm04.interfaces.ReportService;
import asm04.interfaces.Withdraw;

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
        return  String.format("%-5s | %-40s | %-25s ", super.getAccountNumber(), "SAVINGS", str1);
    }

    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("|%35s%-30s| \n", Utils.getTitle()," SAVINGS");
        System.out.printf("| %4s %53s |\n","NGÀY G/D:",Utils.getDateTime());
        System.out.printf("| %6s %55s |\n","ATM ID:","DIGITAL-BANK-ATM 2023");
        System.out.printf("| %6s %56s |\n","SỐ TK:",getAccountNumber());
        System.out.printf("| %4s %51s |\n","SỐ TK NHẬN:",getAccountNumber());
        System.out.printf("| %4s %47s |\n","SỐ TIỀN CHUYỂN:", Utils.formatBalance(amount));
        System.out.printf("| %6s %56s |\n","SỐ DƯ:", Utils.formatBalance(getBalance()));
        System.out.printf("| %4s %52s |\n","PHÍ + VAT:", Utils.formatBalance(0.0));
        System.out.println(Utils.getDivider());

    }

    @Override
    public void logTransferMoney(double amount, String reAccountId) {
        System.out.println(Utils.getDivider());
        System.out.printf("|%35s%-30s| \n", Utils.getTitle()," SAVINGS");
        System.out.printf("| %4s %53s |\n","NGÀY G/D:",Utils.getDateTime());
        System.out.printf("| %6s %55s |\n","ATM ID:","DIGITAL-BANK-ATM 2023");
        System.out.printf("| %6s %56s |\n","SỐ TK:",getAccountNumber());
        System.out.printf("| %4s %51s |\n","SỐ TK NHẬN:",reAccountId);
        System.out.printf("| %4s %47s |\n","SỐ TIỀN CHUYỂN:", Utils.formatBalance(amount));
        System.out.printf("| %6s %56s |\n","SỐ DƯ:", Utils.formatBalance(getBalance()));
        System.out.printf("| %4s %52s |\n","PHÍ + VAT:", Utils.formatBalance(0.0));
        System.out.println(Utils.getDivider());
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

    @Override
    public boolean transferMoney(String reAccountNumber, double amount) {
        double newBalance;
        boolean checkTransferAmount = true;
        if(checkTransferAmount){
            newBalance = getBalance()+amount;
            setBalance(newBalance);
            return true;
        }
        return false;    }
}
