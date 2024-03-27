package asm03.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Bank {
    private final String id;
    private final List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
        this.id = String.valueOf(UUID.randomUUID());
    }

    public String getId() {
        return id;
    }

    public List<Customer> getCustomers() {
        return customers;
    }


    // Add customer list
    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
    }

    //Add accounts to customers in the bank
    public void addNewAccount(String customerId, Account newAccount) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                customer.addAccount(newAccount);
            }
        }
    }

    // check the customer is in the customer list
    public Customer isCustomerExisted(String customerId) {
        for (Customer customer : customers) {
            if(customer.getCustomerId().equals(customerId))
                return customer;
        }
        return null;
    }

}
