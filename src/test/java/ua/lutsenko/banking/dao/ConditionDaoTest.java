package ua.lutsenko.banking.dao;

import org.junit.Before;
import org.junit.Test;
import util.DsUtil;

import static org.junit.Assert.*;

/**
 * Created by Denis Lutsenko.
 */
public class ConditionDaoTest {
    private ConditionDao conditionDao;

    @Before
    public void setUp() throws Exception {
        DsUtil dsUtil = new DsUtil();
        conditionDao = new ConditionDao(dsUtil.getDs());
    }

    @Test
    public void getAccountConditions() throws Exception {
        int userId = 1;
        assertNotNull(conditionDao.getAccountConditions(userId));
    }

    @Test
    public void getConditions() throws Exception {
        int userId = 1;
        String cardType = "CREDIT";
        assertNotNull(conditionDao.getConditions(userId, cardType));
    }

    @Test
    public void showActiveAccounts() throws Exception {
        int userId = 1;
        assertNotNull(conditionDao.showActiveAccounts(userId));
    }

    @Test
    public void getWithdrawalPercent() throws Exception {
        int accountId = 1;
        assertTrue(0.50 == conditionDao.getWithdrawalPercent(accountId));
    }
}