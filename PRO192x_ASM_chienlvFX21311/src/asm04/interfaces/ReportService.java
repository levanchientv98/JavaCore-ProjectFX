package asm04.interfaces;

public interface ReportService {
    void log(double amount);

    void logTransferMoney(double amount, String reAccountId);
}
