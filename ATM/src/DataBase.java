import java.util.*;
import java.util.stream.Stream;


public class DataBase {
    private static List<Account> accountList = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private static Deque<Transaction> transactions = new ArrayDeque<>();


//add a default user to database
    {
        User user = new User("Raha","Fayyaz",24);
        Account account = new Account(user,12345,54321,123000000);
        users.add(user);
        accountList.add(account);
    }


//register a new account by user
    public static void createAccount() {
        Integer a = (Integer) MyMethod.getInput("Enter account number :",Integer.class);
        Integer b = (Integer) MyMethod.getInput("Enter password :",Integer.class);
        double c = 10000;
        Account account = new Account(a, b, c);
        accountList.add(account);
    }
    public void createUser() {
        String a = (String) MyMethod.getInput("Enter your Name :",String.class);
        String b = (String) MyMethod.getInput("Enter your family :",String.class);
        int c = (int) MyMethod.getInput("Enter your age :",int.class);
        User u = new User(a, b, c);
        users.add(u);
    }




    //getters and setters
    public List<Account> getAccountList() {
        return accountList;
    }

    public ArrayList<User> getUsers() {
        return (ArrayList<User>) users;
    }




    //get last 10 transactions
    public ArrayList<Transaction> getTransaction(Integer accNumber) {
        boolean isEmpty = transactions.isEmpty();
        List<Transaction> transaction = new ArrayList<>();
        if (isEmpty == false) {
            transactions.stream()
                    .filter((acc) -> acc.getAccountNumber().equals(accNumber))
                    .forEachOrdered(a -> {
                        while (transaction.size() < 10) {
                            transaction.add(transactions.pollLast());
                        }

                    });
            return (ArrayList<Transaction>) transaction;
        }
        else {
            MyMethod.print("There is no transaction!");
            return null;
        }
    }




/* get the account by account number
    public Account getAccount(Integer accNumber){
        for (Account acc:accountList){
            if (accNumber.equals(acc.getAccountNumber()))
                return acc;
        }
        return null;
    }
*/








    public double showBalance(Integer accNumber){
        for (Account acc:this.accountList){
            if (accNumber.equals(acc.getAccountNumber()))
                return acc.getBalance();

        }
        return 0;
    }



    public void withdraw(Integer accNumber,double amount){
        for (Account acc:accountList){
            if (accNumber.equals(acc.getAccountNumber())){
                acc.setBalance(acc.getBalance()+amount);

            }


        }Transaction with = new Withdraw(accNumber,"Withdraw",amount);
        transactions.add(with);
    }



    public void debit(Integer accNumber , double amount){
        for (Account acc:this.accountList){
            if (accNumber.equals(acc.getAccountNumber())){
                acc.setBalance(acc.getBalance()-amount);
            }


        }
        //add transaction to database
        Transaction dep = new Withdraw(accNumber,"Debit",amount);
        transactions.add(dep);
    }





    @Override
    public String toString() {
        return "DataBase{" +
                "accountList=" + accountList +
                ", users=" + users +
                ", transactions=" + transactions +
                '}';
    }
}
