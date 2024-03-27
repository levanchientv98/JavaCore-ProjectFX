package asm03.models;

import asm03.models.Account;
import asm03.models.Customer;

import java.util.List;
public class DigitalCustomer extends Customer {
    public DigitalCustomer(String name, String customerId) {
        super(customerId, name);
    }

    public boolean withdraw(String accountNumber, double amount) {
        for (Account activeAccount : getAccounts()) {
            if (activeAccount.getAccountNumber().equals(accountNumber)) {
                SavingsAccount sa;
                LoanAccount la;
                boolean status;
                if (activeAccount instanceof SavingsAccount) {
                    sa = (SavingsAccount) activeAccount;
                    status = sa.withdraw(amount);
                    sa.addTransaction(accountNumber, -amount, status);
                    if (status) {
                        sa.log(amount);
                    }
                }
                if (activeAccount instanceof LoanAccount) {
                    la = (LoanAccount) activeAccount;
                    status = la.withdraw(amount);
                    la.addTransaction(accountNumber, -amount, status);
                    if (status) {
                        la.log(amount);
                    }

                }
            }
        }
        return false;
    }
}
