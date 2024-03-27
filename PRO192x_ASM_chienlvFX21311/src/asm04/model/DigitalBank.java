package asm04.model;

import java.util.ArrayList;
import java.util.List;

public class DigitalBank extends Bank {


    public DigitalBank() {
        super();
    }

    public Customer getCustomerById(String customerId) {
        return isCustomerExisted(customerId);
    }

    public void addCustomer(String customerId, String customerName) {
        super.addCustomer(new DigitalCustomer(customerId, customerName));
    }

    public boolean withdraw(String customerId, String accountNumber, double amount, String reAccountId){
        for (Customer customer: getCustomers()){
            if (customer.getCustomerId().equals(customerId)) {
                DigitalCustomer dCustomer = (DigitalCustomer) customer;
                return dCustomer.withdraw(accountNumber, amount, reAccountId);
            }
        }
        return false;
    }

    public boolean transfers(String customerId, String reAccountNumber,double amount){

        for (Customer customer: getCustomers()){
            if (customer.getCustomerId().equals(customerId)) {
                DigitalCustomer dCustomer = (DigitalCustomer) customer;
                return dCustomer.transfers(reAccountNumber, amount);
            }
        }
        return false;
    }

    public void addAccount(String accountId){
        super.addListAccount(accountId);
    }

    public boolean checkCustomerExisted(String customerID){
        return super.checkCustomerExisted(customerID);
    }

    public boolean withdrawATM(String customerId, String accountNumber, double amount ){
        for (Customer customer: getCustomers()){
            if (customer.getCustomerId().equals(customerId)) {
                DigitalCustomer dCustomer = (DigitalCustomer) customer;
                return dCustomer.withdrawATM(accountNumber, amount);
            }
        }
        return false;
    }

}
