package ua.lutsenko.banking.dao;

import org.junit.Before;
import org.junit.Test;
import ua.lutsenko.util.DsUtil;

import java.sql.Date;

import static org.junit.Assert.*;


/**
 * Created by Denis Lutsenko.
 */
public class ApplicationDaoTest {


    DsUtil dsUtil;
    ApplicationDao applicationDao;

    @Before
    public void setUp() throws Exception {
        dsUtil = new DsUtil();
        applicationDao = new ApplicationDao(dsUtil.getDs());
    }

    @Test
    public void insertApplication() throws Exception {
        int userId = 1;
        String accountType = "CREDIT";
        String accountCurrency = "USD";
        Date currDate = Date.valueOf(java.time.LocalDate.now());
        double accountBalance = 100;
        String accountStatus = "PENDING";
        boolean result = applicationDao.insertApplication(userId, accountType, accountCurrency, currDate,
                accountBalance, accountStatus);
        assertTrue(result);
    }

    @Test
    public void getApplications() throws Exception {
        assertEquals(2, applicationDao.getApplications().size());
    }

    @Test
    public void updateApplicationStatus() throws Exception {
        boolean result = applicationDao.updateApplicationStatus(1, "pending");
        assertEquals(true, result);
    }

    @Test
    public void deleteApplication() throws Exception {
        boolean result = applicationDao.deleteApplication(5);
        assertTrue(result);
    }
}