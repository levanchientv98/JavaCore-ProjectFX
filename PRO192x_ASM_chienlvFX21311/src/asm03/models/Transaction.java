package asm03.models;

import asm03.Utils;

import java.util.UUID;


public class Transaction {
    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status;
    public Transaction(String accountNumber, double amount, boolean status){
        this.id=String.valueOf(UUID.randomUUID());
        this.accountNumber=accountNumber;
        this.amount=amount;
        this.time= Utils.getDateTime();
        this.status=status;
    }
    public String getId(){
        return id;
    }
    public String getAccountNumber(){
        return  accountNumber;
    }
    public double getAmount(){
        return amount;
    }
    public String getTime(){
        return time;
    }
    public boolean getStatus(){
        return status;
    }
}
