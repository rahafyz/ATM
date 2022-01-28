package Application;


import Dao.AccountDao;
import Exeptions.AccountNotFoundException;
import Exeptions.InvalidAmountException;
import Exeptions.InvalidInputException;
import Exeptions.NotFoundException;
import util.MyMethod;
import model.Account;
import model.Transaction;
import model.TransactionType;
import model.User;


import java.util.List;
import java.util.Optional;

import static Application.UserApplication.createUser;

public class AccountApplication {
    private static AccountDao accountDao;
    static {
        accountDao = new AccountDao();
    }

    public static Account getAccount(Integer id){
        Optional<Account> account = accountDao.get(id);
        return account.orElseThrow(
                ()-> {
                    throw new NotFoundException("There is no account by this id");
                }
        );
    }

    public static List<Account> getAllAccounts() {
        return accountDao.getAll();
    }

    public static void updateAccount(Account account, String[] params) {
        accountDao.update(account, params);
    }

    public static void saveAccount(Account account) {
        accountDao.save(account);
    }

    public static void deleteAccount(Account account) {
        accountDao.delete(account);
    }


    public static Optional<Account> findAccount(String accountNumber){
        var ref = new Object() {
            Optional<Account> account = Optional.empty();
        };
        accountDao.find_by_accNum(accountNumber).ifPresentOrElse(account1 ->{
            ref.account = Optional.of(account1);
                },
                ()->{
            throw new AccountNotFoundException();
                }
        );
        return ref.account;
    }




    public static Account createAccount() {
        Account account;
        User user = createUser();
        String a = (String) MyMethod.getInput("Enter account number :", String.class);
        String b = (String) MyMethod.getInput("Enter password :", String.class);


        if (accountDao.find_by_accNum(b).isEmpty()) {
            account = new Account(a, b, Account.getMinBalance());
            account.setUser(user);
            accountDao.save(account);
        } else {
            throw new InvalidInputException("this account is already exist.");
        }
        return account;
    }


    public static void deposit(Account account, Double amount){
        account.deposit(amount);
        accountDao.change_balance(account, account.getBalance()+amount);
        Transaction transaction = new Transaction(amount, TransactionType.DEPOSIT,account);
        TransactionApplication.saveTransaction(transaction);

    }

    public static void withdraw(Account account, Double amount){
        if(amount<account.getBalance()) {
            account.withDraw(amount);
            accountDao.change_balance(account, account.getBalance() - amount);
            Transaction transaction = new Transaction(amount,TransactionType.WITHDRAW,account);
            TransactionApplication.saveTransaction(transaction);
        }
        else
            throw new InvalidAmountException();
    }
}
