package asm03.models;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Customer extends User {
    private final List<Account> accounts;

    public Customer(String customerId, String name) {
        super(customerId, name);
        accounts = new ArrayList<Account>();
    }

    // Add accounts that customers do not have.
    public void addAccount(Account newAccount) {
        if (!isAccountExisted(newAccount.getAccountNumber()) ) {
            accounts.add(newAccount);
        }
    }

    //-------------------begin getter and setter--------------------
    public List<Account> getAccounts() {
        return accounts;
    }

//    public void setAccounts(List<Account> accounts) {
//
//        accounts = accounts;
//    }
    //-------------------end getter and setter--------------------

    //check premium account
    public boolean isPremium() {
        for (Account account : accounts) {
            if (account.isPremium()) {
                return true;
            }
        }
        return false;
    }

    //    Function calculate total balance
    public double getTotalBalance() {
        double total = 0;
        for (Account account : accounts) {
            total += account.getBalance();
        }
        return total;
    }

    //check the account is in the account list
    public boolean isAccountExisted(String accountId) {
        for (Account account : accounts) {
            if (Objects.equals(accountId, account.getAccountNumber())) {
                return true;
            }
        }
        return false;
    }


    //The displayInformation function is used to display object the Customer
    public void displayInformation() {
        String customerType;
        for (int i = 0; i <= this.accounts.size() - 1; i++) {
            System.out.println(i + 1 + "\t  " + this.accounts.get(i).toString());
        }
        if (isPremium()) {
            customerType = "Premium";
        } else {
            customerType = "Normal";
        }
        System.out.println(getCustomerId() + " | \t" + getName() + "\t|\t" + customerType + " | \t" + toString1());
    }

    //    Function format total balanace
    public String toString1() {
        // tạo 1 NumberFormat để định dạng tiền tệ theo tiêu chuẩn của Việt Nam
        // đơn vị tiền tệ của Việt Nam là đồng
        String totalBalance;
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        totalBalance = currencyVN.format(getTotalBalance());
        return totalBalance;
    }
    // get account information by account id
    public Account getAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

}
