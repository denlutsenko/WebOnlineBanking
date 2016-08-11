package ua.lutsenko.banking.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DsUtil;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Lutsenko.
 */
public class AddressDaoTest {

    private AddressDao addressDao;

    @Before
    public void setUp() throws Exception {
        DsUtil dsUtil = new DsUtil();
        addressDao = new AddressDao(dsUtil.getDs());
    }

    @Test
    public void isAddressExist() throws Exception {
        int userId = 1;
        assertTrue(addressDao.isAddressExist(userId));
    }

    @Test
    public void insertAddress() throws Exception {
        int userId = 999;
        String country = "aa";
        String city = "bb";
        String street = "cc";
        String houseNumber = "bb/44";
        addressDao = mock(AddressDao.class);
        when(addressDao.insertAddress(userId, country, city, street, houseNumber)).thenReturn(true);
        assertTrue(addressDao.insertAddress(userId, country, city, street, houseNumber));
    }

    @After
    public void tearDown() throws Exception {
    }
}