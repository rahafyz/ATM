package model;

import Exeptions.InvalidAmountException;
import Exeptions.InvalidPasswordException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {
    private Integer id;
    private final static Double MIN_BALANCE = 100d;
    private User user;
    private String accountNumber;
    private String password;
    private Double balance;
    private List<Transaction> transactions;

    public Account( User user, String accountNumber, String password) {
        this.id = id;
        this.user = user;
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = MIN_BALANCE;
    }



    public String getAccountNumber() {
        return accountNumber;
    }


    public User getUser() {
        return user;
    }


    public Double getBalance() {
        return balance;
    }


    public List<Transaction> getTransactions() {
        return transactions;
    }





    @Override
    public String toString() {
        return "model.Account{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                '}';
    }



    public void validation(String pass){
            if( !this.password.equals(pass)){
                throw new InvalidPasswordException();
            }

    }


    public void deposit(Double amount) {
        if (Objects.isNull(transactions)) {
            transactions = new ArrayList<>();
        }
        transactions.add(new Transaction(amount, this, TransactionType.DEPOSIT));
        this.balance += amount;
    }

    public void withDraw(Double amount) {
        if (this.balance - amount < MIN_BALANCE) {
            throw new InvalidAmountException();
        }
        if (Objects.isNull(transactions)) {
            transactions = new ArrayList<>();
        }
        transactions.add(new Transaction(amount, this, TransactionType.WITHDRAW));
        this.balance = this.balance - amount;
    }



}
