package asm02.models;

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
        for (int i = 0; i < customers.size(); i++) {
            if (isCustomerExisted(customerId)) {
                customers.get(i).addAccount(newAccount);
            }
        }
    }

    // check the customer is in the customer list
    public boolean isCustomerExisted(String customerId) {
        for (Customer customer : customers) {
            if (Objects.equals(customerId, customer.getCustomerId())) {
                return true;
            }
        }
        return false;
    }

}
