package ua.lutsenko.banking.entity;

import java.time.LocalDate;

/**
 * Created by Denis Lutsenko.
 */
public class Operation {
    private Integer id;
    private final Account account;
    private String operationType;
    private LocalDate operationDate;
    private double operationSumm;

    public Operation(Account account) {
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }

    public double getOperationSumm() {
        return operationSumm;
    }

    public void setOperationSumm(double operationSumm) {
        this.operationSumm = operationSumm;
    }
}
