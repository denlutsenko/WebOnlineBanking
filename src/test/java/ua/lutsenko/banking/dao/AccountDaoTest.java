package ua.lutsenko.banking.dao;

import org.junit.Test;
import org.mockito.Matchers;
import ua.lutsenko.util.DsUtil;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Lutsenko.
 */
public class AccountDaoTest {
    private DsUtil dsUtil;
    private AccountDao accountDao;


    @Test
    public void getCardCurrency() throws Exception {
        dsUtil = new DsUtil();
        accountDao = new AccountDao(dsUtil.getDs());
    }

    @Test
    public void updateBalance() throws Exception {
        accountDao = mock(AccountDao.class);
        Calendar cal = Calendar.getInstance();
        Timestamp currDate = new Timestamp(cal.getTimeInMillis());
        when(accountDao.updateBalance(1, "gg", currDate, 100)).thenReturn(true);
        assertTrue(accountDao.updateBalance(1, "gg", currDate, 100));
    }

    @Test
    public void blockAccount() throws Exception {
        accountDao = mock(AccountDao.class);
        when(accountDao.blockAccount(Matchers.anyString())).thenReturn(true);
        assertTrue(accountDao.blockAccount(Matchers.anyString()));
    }

    @Test
    public void unblockAccount() throws Exception {
        accountDao = mock(AccountDao.class);
        when(accountDao.unblockAccount(Matchers.anyString())).thenReturn(true);
        assertTrue(accountDao.unblockAccount(Matchers.anyString()));
    }

    @Test
    public void getAccountId() throws Exception {
        accountDao = mock(AccountDao.class);
        when(accountDao.getAccountId(Matchers.anyString())).thenReturn(1);
        assertTrue(accountDao.getAccountId(Matchers.anyString()) == 1);
    }

}