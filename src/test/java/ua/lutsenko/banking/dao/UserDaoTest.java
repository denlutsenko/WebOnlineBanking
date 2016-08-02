package ua.lutsenko.banking.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.lutsenko.banking.entity.User;
import ua.lutsenko.util.DsUtil;

import static org.junit.Assert.*;

/**
 * Created by Denis Lutsenko on 8/1/2016.
 */
public class UserDaoTest {
    private UserDao userDao;
    @Before
    public void setUp() throws Exception {
        DsUtil dsUtil = new DsUtil();
        userDao = new UserDao(dsUtil.getDs());
    }



    @Test
    public void exist() throws Exception {
        String email = "den.lutsenko@gmail.com";
        String password = "d";
        assertTrue(userDao.exist(email, password));
    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void getUserData() throws Exception {

    }

    @After
    public void tearDown() throws Exception {


    }

}