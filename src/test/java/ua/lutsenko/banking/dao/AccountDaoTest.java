package ua.lutsenko.banking.dao;

import org.junit.Before;
import org.junit.Test;
import util.DsUtil;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/**
 * Created by Denis Lutsenko.
 */
public class AccountDaoTest {

    private AccountDao accountDao;
    private DsUtil dsUtil;

    @Before
    public void setUp() throws Exception {
        dsUtil = new DsUtil();
        accountDao = new AccountDao(dsUtil.getDs());
    }

    @Test
    public void doPayment() throws Exception {
        int idCard = 1;
        String operationType = "withdrawal";
        LocalDateTime dateTime = LocalDateTime.now();
        double operationSumm = 1;
        accountDao = mock(AccountDao.class);
        when(accountDao.doPayment(idCard, operationType, dateTime, operationSumm)).thenReturn(true);
        assertTrue(accountDao.doPayment(idCard, operationType, dateTime, operationSumm));
    }

    @Test
    public void getCardCurrency() throws Exception {
        assertEquals("UAH", accountDao.getCardCurrency(1));
    }

    @Test
    public void showBlockedAccounts() throws Exception {
        assertNotNull(accountDao.showBlockedAccounts());
    }
}