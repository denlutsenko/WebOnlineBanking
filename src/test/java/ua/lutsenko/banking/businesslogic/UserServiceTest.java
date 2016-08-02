package ua.lutsenko.banking.businesslogic;

import org.junit.Test;
import org.mockito.Matchers;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.DaoFactory;
import ua.lutsenko.banking.dao.UserDao;
import ua.lutsenko.banking.entity.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Lutsenko on 8/1/2016.
 */
public class UserServiceTest {
    @Test
    public void exist() throws Exception {
        UserService us = mock(UserService.class);
        when(us.exist()).thenReturn(true);
        boolean res = us.exist();
        assertEquals(true, res);
    }

}