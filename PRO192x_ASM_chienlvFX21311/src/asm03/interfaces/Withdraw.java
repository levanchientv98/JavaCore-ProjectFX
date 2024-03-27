package asm03.interfaces;

public interface Withdraw {
    boolean withdraw(double amount);

    boolean transfers(String accountNumber, double amount, String reAccountNumber);

    boolean isAccepted(double amount);
}
