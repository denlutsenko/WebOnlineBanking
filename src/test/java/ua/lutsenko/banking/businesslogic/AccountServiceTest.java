package ua.lutsenko.banking.businesslogic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.lutsenko.banking.entity.Condition;
import ua.lutsenko.banking.entity.Operation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Lutsenko.
 */
public class AccountServiceTest {
    private AccountService accountService;
    private Condition condition;
    private List<Condition> conditionList;

    @Before
    public void setUp() throws Exception {
        accountService = mock(AccountService.class);
        condition = mock(Condition.class);
        conditionList = new ArrayList<>();
        conditionList.add(condition);
        conditionList.add(condition);
        conditionList.add(condition);
    }

    @Test
    public void getAllConditions() throws Exception {
        condition = mock(Condition.class);
        when(accountService.getAllConditions()).thenReturn(conditionList);
        assertEquals(3, conditionList.size());
    }

    @Test
    public void getConditions() throws Exception {
        condition = mock(Condition.class);
        when(accountService.getConditions("debit")).thenReturn(conditionList);
        assertEquals(3, conditionList.size());
    }

    @Test
    public void showActiveAccounts() throws Exception {
        condition = mock(Condition.class);
        when(accountService.showActiveAccounts()).thenReturn(conditionList);
        assertEquals(3, conditionList.size());
    }

    @Test
    public void showAccountHistory() throws Exception {

    }

    @Test
    public void doPayment() throws Exception {
        when(accountService.doPayment()).thenReturn(true);
        boolean result = accountService.doPayment();
        assertEquals(true, result);
    }

    @Test
    public void doInnerTransfer() throws Exception {
        when(accountService.doInnerTransfer()).thenReturn(true);
        boolean result = accountService.doInnerTransfer();
        assertEquals(true, result);
    }

    @Test
    public void isAddressExist() throws Exception {
        when(accountService.isAddressExist()).thenReturn(true);
        boolean result = accountService.isAddressExist();
        assertEquals(true, result);
    }

    @Test
    public void insertApplication() throws Exception {
        when(accountService.insertApplication()).thenReturn(true);
        boolean result = accountService.insertApplication();
        assertEquals(true, result);
    }

    @Test
    public void blockAccount() throws Exception {
        when(accountService.blockAccount()).thenReturn(true);
        boolean result = accountService.blockAccount();
        assertEquals(true, result);
    }

    @Test
    public void updateBalance() throws Exception {
        when(accountService.updateBalance()).thenReturn(true);
        boolean result = accountService.updateBalance();
        assertEquals(true, result);
    }

    @Test
    public void showBlockedAccounts() throws Exception {

    }

    @Test
    public void unblockAccount() throws Exception {
        when(accountService.unblockAccount()).thenReturn(true);
        boolean result = accountService.unblockAccount();
        assertEquals(true, result);
    }


    @Test
    public void createAccount() throws Exception {
        when(accountService.createAccount()).thenReturn(true);
        boolean result = accountService.createAccount();
        assertEquals(true, result);
    }

    @Test
    public void createConditions() throws Exception {
        when(accountService.createConditions()).thenReturn(true);
        boolean result = accountService.createConditions();
        assertEquals(true, result);
    }

    @Test
    public void updateApplicationStatus() throws Exception {
        when(accountService.updateApplicationStatus("pending")).thenReturn(true);
        boolean result = accountService.updateApplicationStatus("pending");
        assertEquals(true, result);
    }

    @Test
    public void deleteApplication() throws Exception {
        when(accountService.deleteApplication()).thenReturn(true);
        boolean result = accountService.deleteApplication();
        assertEquals(true, result);
    }

    @After
    public void tearDown() throws Exception {

    }
}