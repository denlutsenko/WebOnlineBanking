package ua.lutsenko.banking.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DsUtil;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Denis Lutsenko.
 */
public class OperationDaoTest {
    private OperationDao operationDao;
    private DsUtil dsUtil;

    @Before
    public void setUp() throws Exception {
        dsUtil = new DsUtil();
        operationDao = new OperationDao(dsUtil.getDs());
    }

    @Test
    public void showAccountHistory() throws Exception {
        assertNotNull(operationDao.showAccountHistory(1));
    }

    @After
    public void tearDown() throws Exception {
    }
}