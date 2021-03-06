package Service;

import DBControl.BankAccountDAO;
import Object.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/18/13
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    static BankAccountDAO bankAccountDAO;
    public static void setBankAccountDAO(BankAccountDAO mockDAO) {
        bankAccountDAO = mockDAO;
        //To change body of created methods use File | Settings | File Templates.
    }

    public static BankAccountDTO openAccount(String accountNumber) {
        BankAccountDTO account = new BankAccountDTO(accountNumber);
        bankAccountDAO.save(account);
        return account;
        //To change body of created methods use File | Settings | File Templates.
    }


    public static BankAccountDTO getAccount(String accountNumber) {

        return bankAccountDAO.getAccount(accountNumber);  //To change body of created methods use File | Settings | File Templates.
    }

    public static void doDeposit(String accountNumber, double amount, String des) {
        BankAccountDTO account = BankAccount.getAccount(accountNumber);
        account.setBalance(amount);
        bankAccountDAO.save(account);
        Transaction.createTransaction(accountNumber,amount,des, true);
    }

    public static void doWithDraw(String accountNumber, double amount, String des) {
        BankAccountDTO account = BankAccount.getAccount(accountNumber);
        account.setBalance(-amount);
        bankAccountDAO.save(account);
        Transaction.createTransaction(accountNumber,amount,des, false);
    }

    public static List<TransactionDTO> getTransactions(String accountNumber) {
        //To change body of created methods use File | Settings | File Templates.
        return Transaction.getManyTransaction(accountNumber);
    }

    public static List<TransactionDTO> getTransactions(String accountNumber, long start, long end) {
        return Transaction.getManyTransaction(accountNumber,start,end);
    }

    public static List<TransactionDTO> getTransactions(String accountNumber, int count) {
        return Transaction.getManyTransaction(accountNumber, count);
        //To change body of created methods use File | Settings | File Templates.
    }
}
