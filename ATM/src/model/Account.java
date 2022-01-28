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

    public Account(String accountNumber, String password,Double balance) {
        this.id = id;
        this.user = user;
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }



    public static Double getMinBalance(){
        return MIN_BALANCE;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                "name=" + user.getName()+" "+user.getFamily()+
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                "}\n";
    }

    public void validation(String pass){
            if( !this.password.equals(pass)){
                throw new InvalidPasswordException();
            }

    }


    public void deposit(Double amount) {
        this.balance += amount;
    }

    public void withDraw(Double amount) {
        this.balance = this.balance - amount;
    }



}
