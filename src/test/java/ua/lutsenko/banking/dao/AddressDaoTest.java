package ua.lutsenko.banking.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.lutsenko.util.DsUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    @Test
    public void insertAddress() throws Exception {
        int id = 6;
        String country = "ua";
        String city = "ua";
        String street = "ua";
        String houseNumber = "ua";
        assertTrue(addressDao.insertAddress(id, country, city, street, houseNumber));
    }

    @After
    public void tearDown() throws Exception {
    }
}
