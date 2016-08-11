package ua.lutsenko.banking.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DsUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Denis Lutsenko.
 */
public class OperationDaoTest {
    private DsUtil dsUtil;
    private OperationDao operationDao;

    @Before
    public void setUp() throws Exception {
        dsUtil = new DsUtil();
        operationDao = new OperationDao(dsUtil.getDs());
    }

    @Test
    public void showAccountHistory() throws Exception {
        assertNotNull(operationDao.showAccountHistory(1));
    }

    @Test
    public void insertOperation() throws Exception {
        int cardId = 1;
        String operType = "testWithdrawal";
        LocalDateTime currDate = LocalDateTime.now();
        double summ = 343.34;
        assertTrue(operationDao.insertOperation(dsUtil.getDs().getConnection(), cardId, operType, currDate, summ));
    }


    @After
    public void tearDown() throws Exception {
        // see current id in table operation.
        int id =  127;
        assertTrue(operationDao.deleteOperation(id));
    }
}