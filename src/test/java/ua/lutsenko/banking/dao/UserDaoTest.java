package ua.lutsenko.banking.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.lutsenko.banking.entity.User;
import util.DsUtil;

import static org.junit.Assert.*;

/**
 * Created by Denis Lutsenko.
 */
public class UserDaoTest {
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        DsUtil dsUtil = new DsUtil();
        userDao = new UserDao(dsUtil.getDs());
    }

    @Test
    public void insert() throws Exception {
        String firstName = "aa";
        String lastName = "aa";
        String middleName = "aa";
        String phone = "+380953222096";
        String email = "a@gmail.com";
        String password = "ppp";
        assertTrue(userDao.insert(firstName, lastName, middleName, phone, email, password));
    }

    @Test
    public void exist() throws Exception {
        String email = "den.lutsenko@gmail.com";
        String password = "d";
        assertTrue(userDao.exist(email, password));
    }

    @Test
    public void getUserData() throws Exception {
        String email = "den.lutsenko@gmail.com";
        String password = "d";
        User user = userDao.getUserData(email, password);
        assertNotNull(user);
    }

    @After
    public void tearDown() throws Exception {
        String email = "a@gmail.com";
        String password = "ppp";
        assertTrue(userDao.deleteUser(email, password));
    }
}