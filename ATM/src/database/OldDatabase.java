package database;


public class OldDatabase {
   /* private  List<Account> accounts;

    public OldDatabase() {
        accounts = new ArrayList<>();
//        accounts.add(new Account(new User("Raha","Fayyaz","00227738" , DateFunctions.stringToDate("15-03-1998"))
//        ,"12345","54321"));
    }



//register a new account by user
    public Account createAccount() {
        User user = createUser();
        String a = (String) MyMethod.getInput("Enter account number :",String.class);
        String b = (String) MyMethod.getInput("Enter password :",String.class);
        Account account = new Account(user,a,b);
        this.accounts.add(account);
        return account;
    }


    public User createUser() {
        String fName = (String) MyMethod.getInput("Enter your Name :",String.class);
        String lName = (String) MyMethod.getInput("Enter your family :",String.class);
        String nId  = (String) MyMethod.getInput("Enter your national ID :",String.class);

        System.out.print("Enter your date of birth (dd-MM-yyyy): ");
        Date bDay = DateFunctions.getDate();




        //age validation
        if (DateFunctions.getPeriodByYear(bDay) < 18) {
            throw  new AgeException();
        }


        User user = new User(fName, lName, nId , bDay);
        MyMethod.print(user);
        return user;
    }



    //getters
    public List<Account> getAccountList() {
        return accounts;
    }






// get the account by account number
    public Optional<Account> findAccount(String accNumber){
        return accounts.stream().filter(account ->
                        account.getAccountNumber().equals(accNumber))
                .findFirst();
    }



//get last 10 transactions
    public List<Transaction> LastTenTransactions(String accountNumber) {
        List<Transaction> transactions = new ArrayList<>(10);
        findAccount(accountNumber).ifPresentOrElse(account -> {
            transactions.addAll(account.getTransactions().stream().
                            sorted(Comparator.comparing(Transaction::getDate).reversed())
                                    .collect(Collectors.toList())
                    );
        }, () -> {
            throw new NotFoundException("There is no transaction");
        });
        return transactions;
    }




 @Override
    public String toString() {
        return "database.Database{" +
                "accounts=" + accounts +
                '}';
    }
*/
}
