package ua.lutsenko.banking.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DsUtil;

import static org.junit.Assert.*;

/**
 * Created by Denis Lutsenko.
 */
public class AddressDaoTest {
    private AddressDao addressDao;
    DsUtil dsUtil;

    @Before
    public void setUp() throws Exception {
        dsUtil = new DsUtil();
        addressDao = new AddressDao(dsUtil.getDs());
    }

    @Test
    public void isAddressExist() throws Exception {
        int userId = 1;
        assertTrue(addressDao.isAddressExist(userId));
    }

    @After
    public void tearDown() throws Exception {
    }
}