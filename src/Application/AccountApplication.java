package Application;

import Dao.Dao;
import Dao.AccountDao;
import Exeptions.NotFoundException;
import model.Account;


import java.util.List;
import java.util.Optional;

public class AccountApplication {
    private static Dao<Account> accountDao;
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
}
