import DBControl.TransactionDAO;
import Service.BankAccount;
import Service.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;
import DBControl.BankAccountDAO;
import Object.*;

import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/18/13
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestTransaction {
    public BankAccountDAO mockDAO = mock(BankAccountDAO.class);
    public TransactionDAO mockTDAO= mock(TransactionDAO.class);
    public Calendar mockCalendar = mock(Calendar.class);
    public String accountNumber="123456";
    @Before
    public void setUp()
    {
        reset(mockDAO);
        reset(mockTDAO);
        BankAccount.setBankAccountDAO(mockDAO);
        Transaction.setTransactionDAO(mockTDAO);
        TransactionDTO.setMockCalendar(mockCalendar);
    }
    @Test
    public void testAccountDeposit()
    {
        ArgumentCaptor<BankAccountDTO> ac = ArgumentCaptor.forClass(BankAccountDTO.class);
        BankAccountDTO account = BankAccount.openAccount(accountNumber);
        when(mockDAO.getAccount(accountNumber)).thenReturn(account);
        BankAccount.doDeposit(accountNumber,100.0, "Them 100k");
        verify(mockDAO,times(2)).save(ac.capture());
        List<BankAccountDTO> list = ac.getAllValues();
        assertEquals(list.get(1).getAccountNumber(),accountNumber);
        assertEquals(list.get(1).getBalance(), 100.0);

    }
    @Test
    public void testAccountDepositSaveToDB()
    {
        ArgumentCaptor<TransactionDTO> act = ArgumentCaptor.forClass(TransactionDTO.class);
        BankAccountDTO account = BankAccount.openAccount(accountNumber);
        when(mockDAO.getAccount(accountNumber)).thenReturn(account);
        when(mockCalendar.getTimeInMillis()).thenReturn(1000l);
        BankAccount.doDeposit(accountNumber,100.0, "Them 100k");
        verify(mockTDAO).save(act.capture());
        assertEquals(accountNumber, act.getValue().getAccountNumber());
        assertEquals(100.0, act.getValue().getAmount());
        assertEquals("Them 100k", act.getValue().getDes());
        assertEquals(1000l, act.getValue().getTimeStamp());
    }
}