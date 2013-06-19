package Service;

import DBControl.TransactionDAO;
import Object.*;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/19/13
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction {
    static TransactionDAO transactionDAO;
    public static void setTransactionDAO(TransactionDAO mockTDAO) {
        transactionDAO=mockTDAO;
        //To change body of created methods use File | Settings | File Templates.
    }

    public static void createTransaction(String accountNumber, double amount, String des) {
        TransactionDTO transaction = new TransactionDTO(accountNumber, amount,des);
        transactionDAO.save(transaction);
        //To change body of created methods use File | Settings | File Templates.
    }
}
