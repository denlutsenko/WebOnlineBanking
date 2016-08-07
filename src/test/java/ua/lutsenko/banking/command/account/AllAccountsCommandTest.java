package ua.lutsenko.banking.command.account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.lutsenko.banking.command.RequestWrapper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Lutsenko.
 */
public class AllAccountsCommandTest {
    AllAccountsCommand allAccountsCommand;
    RequestWrapper wrapper;

    @Before
    public void setUp() throws Exception {
        allAccountsCommand = mock(AllAccountsCommand.class);
        wrapper = mock(RequestWrapper.class);
    }


    @Test
    public void execute() throws Exception {
        when(allAccountsCommand.execute(wrapper)).thenReturn("/jsp/accountPages/allAccounts.jsp");
        assertEquals("/jsp/accountPages/allAccounts.jsp", allAccountsCommand.execute(wrapper));
    }


    @After
    public void tearDown() throws Exception {

    }

}