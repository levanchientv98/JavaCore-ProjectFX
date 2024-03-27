package asm04.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private final String id;
    private final List<Customer> customers;
    private final List<String> listAccountId = new ArrayList<String>();



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
    //find customer by accountId
    public String findCustomerByAccountId(String accountId){
       for (int i = 0; i < customers.size(); i++){
           for (int j = 0; j <customers.get(i).getAccounts().size();j++ ){
             String accountNB = customers.get(i).getAccounts().get(j).getAccountNumber();
             if(accountId.equals(accountNB)){
                 return customers.get(i).getCustomerId();
             }
           }
       }
        return null;
    }

    public void addListAccount(String newAccount) {
        listAccountId.add(newAccount);
    }

    //check the account is in the account list
    public boolean isAccountEx(String accountId) {
        for (String account : listAccountId) {
            if (listAccountId.contains(accountId)) {
                return true;
            }
        }
        return false;
    }

    // check the customer is in the customer list
    public Customer isCustomerExisted(String customerId) {
        for (Customer customer : customers) {
            if(customer.getCustomerId().equals(customerId))
                return customer;
        }
        return null;
    }

    public boolean checkCustomerExisted(String customerId) {
        for (Customer customer : customers) {
            if(customer.getCustomerId().equals(customerId))
                return true;
        }
        return false;
    }

}
