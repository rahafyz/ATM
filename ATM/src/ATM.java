
import java.util.ArrayList;

public class ATM {
    static DataBase dataBase = new DataBase();
    static Account account = new Account();
    private static int loginAccountNum;

    public static Account login() {
        Integer accountNumber = (Integer) MyMethod.getInput("please Enter your account Number:", Integer.class);
        Integer password = (Integer) MyMethod.getInput("please enter your password:", Integer.class);
        boolean state = account.validation(accountNumber, password);
        if (state == true) {
            loginAccountNum = accountNumber;
            MyMethod.print("Login Success! Loading the menu...");
        } else {
            MyMethod.print("Invalid username or password, please try again..");
            login();
        }

        return null;
    }

    Deposit deposit = new Deposit(loginAccountNum,dataBase);
    Withdraw withdraw = new Withdraw(loginAccountNum,dataBase);

    public void showMenu() {
        int select = (int) MyMethod.getInput("1-Show Balance\n" +
                "2-Deposit\n" +
                "3-Withdraw\n" +
                "4-Get last 10 transactions\n"+
                "5-Exit\n"+
                "Choose An Action: ",int.class);
        boolean exit = true;
        while (exit) {
            switch (select) {
                case 1 -> {
                    MyMethod.print("your balance:"+dataBase.showBalance(loginAccountNum));
                    exit = false;
                    showMenu();
                }
                case 2 -> {
                    deposit.changeBalance(loginAccountNum);
                    exit = false;
                    MyMethod.print("your balance:"+dataBase.showBalance(loginAccountNum));
                    showMenu();
                }
                case 3 -> {
                    withdraw.changeBalance(loginAccountNum);
                    exit = false;
                    MyMethod.print("your balance:"+dataBase.showBalance(loginAccountNum));
                    showMenu();
                }
                case 4 -> {
                    MyMethod.print(dataBase.getTransaction(loginAccountNum));
                    exit = false;
                    showMenu();
                }
                case 5 ->{
                    exit = false;
                    MyMethod.print("Thank you!");
                    System.exit(0);
                }
                default ->{
                    MyMethod.print("You did not enter a valid selection. Try again.");
                    exit = false;
                    showMenu();
                }
            }
        }
    }

    public void menu(){
        int select = (int) MyMethod.getInput("1-Login as a member\n2-Create a new account",int.class);
        switch (select){
            case 1 -> {
                login();
            }
            case 2 ->{
                DataBase.createAccount();
                login();
            }
            default -> {
                MyMethod.print("You did not enter a valid selection. Try again.");
                menu();
            }
        }
        showMenu();
    }


}
