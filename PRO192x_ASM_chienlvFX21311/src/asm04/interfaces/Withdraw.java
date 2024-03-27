package asm04.interfaces;

public interface Withdraw {
    boolean withdraw(double amount);

    boolean isAccepted(double amount);

    boolean transferMoney(String reAccountNumber, double amount);
}
