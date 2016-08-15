package ua.lutsenko.banking.currencymanager;

import java.util.ResourceBundle;

/**
 * Created by Denis Lutsenko.
 */

/**
 * This class calculating new values of operations between two cards.
 */
public class CurrencyConversion {
    private final static ResourceBundle EXCHANGE_RATES = ResourceBundle.getBundle("currentExchangeRates");

    /**
     * This method recalculates new value of operation.
     *
     * @param currFrom parameter contains currency value of main account.
     * @param currTo   parameter contains currency value of another account.
     * @param opSumm   parameter contains operation sum.
     * @return new value of operation sum.
     */
    public double getCurrencyConverter(String currFrom, String currTo, double opSumm) {
        String tmp1 = EXCHANGE_RATES.getString(currFrom);
        String tmp2 = EXCHANGE_RATES.getString(currTo);
        double currencyFrom = Double.parseDouble(tmp1.trim());
        double currencyTo = Double.parseDouble(tmp2.trim());
        double result;
        switch (currFrom) {
            case "USD":
                result = (opSumm * currencyFrom) / currencyTo;
                System.out.println();
                break;
            case "EUR":
                result = (opSumm * currencyFrom) / currencyTo;
                break;
            case "UAH":
                result = (opSumm * currencyFrom) / currencyTo;
                break;
            default:
                result = opSumm;
        }
        return Double.parseDouble(String.format("%.2f", result));
    }

    /**
     * Tis method recalculate new value of operation sum(value plus percent).
     *
     * @param opSum operation sum.
     * @param percent percent of withdrawal.
     * @return new value(sum).
     */
    public double calculateWithdrawalPercent(double opSum, double percent) {
        double result = opSum;
        result += (opSum * percent) / 100;
        return Double.parseDouble(String.format("%.2f", result));
    }
}
