package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

    private Integer id;

    private Double amount;

    private Account account;

    private TransactionType transactionType;

    private Date date;

    DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");


    public Transaction(Double amount, Account account, TransactionType transactionType) {
        this.amount = amount;
        this.account = account;
        this.transactionType = transactionType;
        this.date = new Date();
    }

    public Double getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }


    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "model.Transaction{" +
                "amount=" + amount +
                ", transactionType=" + transactionType +
                ", date=" + df.format(date) +
                '}';
    }
}