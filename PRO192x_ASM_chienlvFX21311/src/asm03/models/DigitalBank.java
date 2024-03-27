package asm03.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public boolean withdraw(String customerId, String accountNumber, double amount){
        for (Customer customer: getCustomers()){
            if (customer.getCustomerId().equals(customerId)) {
                DigitalCustomer dCustomer = (DigitalCustomer) customer;
                return dCustomer.withdraw(accountNumber, amount);
            }
        }
        return false;
    }

}
