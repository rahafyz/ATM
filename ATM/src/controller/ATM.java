package controller;

import Application.AccountApplication;
import Application.TransactionApplication;
import Exeptions.AgeException;
import util.MyMethod;
import database.DbConnector;
import model.Account;

public class ATM {

    private static final int LOGIN = 1;
    private static final int REGISTER = 2;


    private static final int BALANCE = 1;
    private static final int DEPOSIT = 2;
    private static final int WITHDRAW = 3;
    private static final int TRANSACTIONS = 4;
    private static final int EXIT = 5;



    private DbConnector dbConnector;


    Account currentAccount;
    private boolean isAuthenticated = false;
    private boolean isUserExited = false;

    public ATM() {
        this.dbConnector = new DbConnector();
    }


    public void login(String accountNumber, String password) {
        AccountApplication.findAccount(accountNumber).stream().forEach(account -> {
            account.validation(password);
            currentAccount = account;
        });
    }

    public void run(){
        firstMenu();
        while (!isUserExited && isAuthenticated) {
            try {
                Integer item = (Integer) MyMethod.getInput("1-Show Balance\n" +
                        "2-Deposit\n" +
                        "3-Withdraw\n" +
                        "4-Get last 10 transactions\n"+
                        "5-Exit\n"+
                        "get item number :",Integer.class);
                mainMenu(item);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                run();
            }
        }
    }






    //for logging or registering
    public void firstMenu() {
        int select = (int) MyMethod.getInput("1-Login as a member\n2-Create a new account", int.class);
        switch (select) {
            case LOGIN -> {
                while (!isAuthenticated) {
                    try {
                        String accountNumber = (String) MyMethod.getInput("please Enter your account Number:",String.class);
                        String password = (String) MyMethod.getInput("please enter your password:", String.class);
                        login(accountNumber, password);
                        isAuthenticated = true;
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
            case REGISTER -> {
                try {
                    currentAccount = AccountApplication.createAccount();
                    isAuthenticated = true;
                }catch (AgeException e){
                    System.err.println(e.getMessage());
                    System.exit(0);
                }catch (Exception e){
                    System.err.println(e.getMessage());
                    firstMenu();
                }

            }
            default -> {
                MyMethod.print("You did not enter a valid selection. Try again.");
                firstMenu();
            }
        }
    }

    public void mainMenu(Integer item){
        switch (item) {
            case BALANCE -> MyMethod.print("your balance is:"+
                    currentAccount.getBalance()+
                    "$");
            case DEPOSIT -> {
                deposit();
                MyMethod.print("your balance is:"+
                        currentAccount.getBalance()+
                        "$");
            }
            case WITHDRAW -> {
                withdraw();
                MyMethod.print("your balance is:"+
                        currentAccount.getBalance()+
                        "$");
            }
            case TRANSACTIONS -> MyMethod.print(TransactionApplication.getTransactions(currentAccount.getId()));
            case EXIT -> isUserExited = true;
        }
    }

    public void deposit() {
            Double amount = (Double) MyMethod.getInput("Please Enter amount:",Double.class);
            AccountApplication.deposit(currentAccount,amount);
    }

    public void withdraw() {
        try {
        Double amount = (Double) MyMethod.getInput("Please Enter amount:",Double.class);
        AccountApplication.withdraw(currentAccount,amount);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}


