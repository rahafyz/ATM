import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Account {
    static User user = new User();
    private Integer accountNumber;
    private Integer password;
    private double balance;
    private String accType;

    public Account(User user){
        this.user = user;
    }

    public Account(Integer accountNumber, Integer password, double balance) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
    }

    public Account(User user,Integer accountNumber, Integer password, double balance) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Account() {
    }

    public static User getUser() {
        return user;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", password=" + password +
                ", accType='" + accType + '\'' +
                '}';
    }



    public boolean validation(Integer acc,Integer pass){
        DataBase dataBase = new DataBase();
        for(Account u:dataBase.getAccountList()){
            if(u.getAccountNumber().equals(acc) && u.getPassword().equals(pass)){
                return true;
            }


        }return false;


    }



//    public void Deposit(double d){
//        balance =+ d;
//    }
//    public void WithDraw(double w){
//        balance =- w;
//    }
//    public void ShowBalance(){
//        System.out.println("Your Balance : "+balance);
//    }

}
