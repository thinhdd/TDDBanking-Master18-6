import DBControl.BankAccountDAO;
import Service.BankAccount;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import Object.*;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/18/13
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestAccount {
    public BankAccountDAO mockDAO = mock(BankAccountDAO.class);
    public String accountNumber="123456";
    @Before
    public void setUp()
    {
        reset(mockDAO);
        BankAccount.setBankAccountDAO(mockDAO);
    }
    @Test
    public void testOpenAccount()
    {
        ArgumentCaptor<BankAccountDTO> ac = ArgumentCaptor.forClass(BankAccountDTO.class);
        BankAccount.openAccount(accountNumber);
        verify(mockDAO).save(ac.capture());
        assertEquals(ac.getValue().getAccountNumber(), accountNumber);
        assertEquals(ac.getValue().getBalance(), 0.0);
    }
    @Test
    public void testGetAccount()
    {
        ArgumentCaptor<BankAccountDTO> ac = ArgumentCaptor.forClass(BankAccountDTO.class);
        BankAccountDTO account = BankAccount.openAccount(accountNumber);
        when(mockDAO.getAccount(accountNumber)).thenReturn(account);
        BankAccountDTO accountresult = BankAccount.getAccount(accountNumber);
        verify(mockDAO).getAccount(accountNumber);
        assertEquals(accountresult.getAccountNumber(), accountNumber);
        assertEquals(accountresult.getBalance(), 0.0);
    }
}
