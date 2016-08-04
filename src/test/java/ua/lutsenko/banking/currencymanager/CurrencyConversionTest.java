package ua.lutsenko.banking.currencymanager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Denis Lutsenko on 8/4/2016.
 */
public class CurrencyConversionTest {
    private CurrencyConversion cc;
    @Before
    public void setUp() throws Exception {
        cc = new CurrencyConversion();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getCurrencyConverter() throws Exception {
        double result = cc.getCurrencyConverter("USD", "EUR", 100);
        Assert.assertTrue(result == 78.59);
    }

    @Test
    public void calculateAndSumWithdrawalPercent() throws Exception {
        double result = cc.calculateWithdrawalPercent(100, 1.5);
        System.out.println(result);
        Assert.assertTrue(result == 101.5);
    }

}