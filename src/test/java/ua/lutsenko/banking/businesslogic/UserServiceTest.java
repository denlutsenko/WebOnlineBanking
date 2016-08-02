package ua.lutsenko.banking.businesslogic;

import org.junit.Before;
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
 * Created by Denis Lutsenko.
 */
public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = mock(UserService.class);
    }

    @Test
    public void getUserData() throws Exception {
        when(userService.getUserData()).thenReturn(new User("den", "lutsenko", "sehiyovuch"));
        User user = userService.getUserData();
        String firstName = "den";
        String lastName = "lutsenko";
        String middleName = "sehiyovuch";
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(middleName, user.getMiddleName());

    }

    @Test
    public void insertUser() throws Exception {
        when(userService.insertUser()).thenReturn(true);
        assertEquals(true, userService.insertUser());
    }

    @Test
    public void insertAddress() throws Exception {
        when(userService.insertAddress()).thenReturn(true);
        boolean result = userService.insertAddress();
        assertEquals(true, result);
    }

    @Test
    public void exist() throws Exception {
        when(userService.exist()).thenReturn(true);
        boolean result = userService.exist();
        assertEquals(true, result);
    }

}