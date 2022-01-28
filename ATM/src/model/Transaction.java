package model;

import java.util.Date;

public class Transaction {

    private Long id;

    private Double amount;

    private Account account;

    private TransactionType transactionType;

    private Date date;



    public Transaction(Double amount, TransactionType transactionType,Account account) {
        this.amount = amount;
        this.account = account;
        this.transactionType = transactionType;
        this.date = new Date();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "Account number=" + account.getAccountNumber() +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", date=" + date +
                "}\n";
    }
}