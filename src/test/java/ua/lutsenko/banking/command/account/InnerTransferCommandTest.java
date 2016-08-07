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
public class InnerTransferCommandTest {
    private InnerTransferCommand innerTransferCommand;
    private RequestWrapper wrapper;

    @Before
    public void setUp() throws Exception {
        innerTransferCommand = mock(InnerTransferCommand.class);
        wrapper = mock(RequestWrapper.class);
    }


    @Test
    public void execute() throws Exception {
        when(innerTransferCommand.execute(wrapper)).thenReturn("/jsp/userPages/personalCabinet.jsp");
        assertEquals("/jsp/userPages/personalCabinet.jsp", innerTransferCommand.execute(wrapper));
    }

    @After
    public void tearDown() throws Exception {

    }
}