package Application;

import Dao.Dao;
import Dao.TransactionDao;
import Exeptions.NotFoundException;
import model.Account;
import model.Transaction;

import java.util.List;
import java.util.Optional;

public class TransactionApplication {
    private static Dao<Transaction> transactionDao;

    static {
        transactionDao = new TransactionDao();
    }

    public static Transaction getTransaction(Integer id){
        Optional<Transaction> transaction = transactionDao.get(id);
        return transaction.orElseThrow(
                ()-> {
                    throw new NotFoundException("There is no transaction.");
                }
        );
    }

    public static List<Transaction> getAllTransactions() {
        return transactionDao.getAll();
    }

    public static void updateTransaction(Transaction transaction , String[] params) {
        transactionDao.update(transaction, params);
    }

    public static void saveTransaction(Transaction transaction) {
        transactionDao.save(transaction);
    }

    public static void deleteTransaction(Transaction transaction) {
        transactionDao.delete(transaction);
    }


}
