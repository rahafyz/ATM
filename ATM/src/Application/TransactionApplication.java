package Application;

import Dao.TransactionDao;
import Exeptions.NotFoundException;
import model.Account;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionApplication {
    private static TransactionDao transactionDao;

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

    public static List<Transaction> getTransactions(Integer id){
        if(Objects.isNull(transactionDao.getTransactions(id)))
            throw new NotFoundException("There is no transaction!");
        return transactionDao.getTransactions(id).stream().limit(10).collect(Collectors.toList());
    }
}
