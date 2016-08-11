package ua.lutsenko.banking.dao;

import org.junit.Before;
import org.junit.Test;
import util.DsUtil;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Lutsenko.
 */
public class ApplicationDaoTest {
    private ApplicationDao applicationDao;
    private DsUtil dsUtil;

    @Before
    public void setUp() throws Exception {
        dsUtil = new DsUtil();
        applicationDao = new ApplicationDao(dsUtil.getDs());
    }

    @Test
    public void insertApplication() throws Exception {
        LocalDate currDate = LocalDate.now();
        applicationDao = mock(ApplicationDao.class);
        when(applicationDao.insertApplication(1, "CREDIT", "USD", currDate, 1.00, "REJECTED")).thenReturn(true);
        assertTrue(applicationDao.insertApplication(1, "CREDIT", "USD", currDate, 1.00, "REJECTED"));
    }

    @Test
    public void getApplications() throws Exception {
        assertNotNull(applicationDao.getApplications());
    }

    @Test
    public void updateApplicationStatus() throws Exception {
        int applicationId = 3;
        String status = "CONFIRMED";
        assertTrue(applicationDao.updateApplicationStatus(applicationId, status));
    }

    @Test
    public void deleteApplication() throws Exception {
        applicationDao = mock(ApplicationDao.class);
        when(applicationDao.deleteApplication(1)).thenReturn(true);
        assertTrue(applicationDao.deleteApplication(1));
    }
}