package asm04.model;

public class DigitalCustomer extends Customer {
    public DigitalCustomer(String customerId, String name) {
        super(customerId, name);
    }

    public boolean withdraw(String accountNumber, double amount,String reAccountId) {
        for (Account activeAccount : getAccounts()) {
            if (activeAccount.getAccountNumber().equals(accountNumber)) {
                SavingsAccount sa;
                LoanAccount la;
                boolean status;
                String type = "TRANSFERS";
                if (activeAccount instanceof SavingsAccount) {
                    sa = (SavingsAccount) activeAccount;
                    status = sa.withdraw(amount);
                    sa.addTransaction(accountNumber, -amount, status,type);
                    if (status) {
                        sa.logTransferMoney(amount,reAccountId);
                    }
                }
                if (activeAccount instanceof LoanAccount) {
                    la = (LoanAccount) activeAccount;
                    status = la.withdraw(amount);
                    la.addTransaction(accountNumber, -amount, status,type);
                    if (status) {
                        la.log(amount);
                    }

                }
            }
        }
        return false;
    }

    public boolean transfers(String reAccountNumber, double amount){
        SavingsAccount sa;
        boolean status;
        String type = "DEPOSIT";
        for (Account activeAccount : getAccounts()) {
            if (activeAccount.getAccountNumber().equals(reAccountNumber)) {
                if (activeAccount instanceof SavingsAccount) {
                    sa = (SavingsAccount) activeAccount;
                    status = sa.transferMoney(reAccountNumber,amount);
                    sa.addTransaction(reAccountNumber, +amount, status,type);
//                    sa.logTransferMoney(amount,reAccountNumber);
                }
            }
        }
        return  false;
    }

    public boolean withdrawATM(String accountNumber, double amount){
        SavingsAccount sa;
        boolean status;
        String type = "WITHDRAW";
        for (Account activeAccount : getAccounts()) {
            if (activeAccount.getAccountNumber().equals(accountNumber)) {
                if (activeAccount instanceof SavingsAccount) {
                    sa = (SavingsAccount) activeAccount;
                    status = sa.withdraw(amount);
                    sa.addTransaction(accountNumber, -amount, status,type);
                    sa.log(amount);
                }
            }
        }
        return  false;
    }
}
