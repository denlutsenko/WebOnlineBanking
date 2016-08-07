package ua.lutsenko.banking.command.account;

import org.junit.Test;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.ConditionDao;
import ua.lutsenko.banking.dao.DaoFactory;
import ua.lutsenko.banking.entity.Condition;
import util.DsUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Lutsenko.
 */
public class BlockAccountCommandTest {
    @Test
    public void execute() throws Exception {
        RequestWrapper wrapper = mock(RequestWrapper.class);
        BlockAccountCommand blockAccountCommand = mock(BlockAccountCommand.class);
         when(blockAccountCommand.execute(wrapper)).thenReturn("/jsp/accountPages/blockCardForm.jsp");
        assertEquals("/jsp/accountPages/blockCardForm.jsp", blockAccountCommand.execute(wrapper));
    }
}