package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.currencymanager.CurrencyConversion;
import ua.lutsenko.banking.dao.AccountDao;
import ua.lutsenko.banking.dao.ConditionDao;
import ua.lutsenko.banking.dao.DaoFactory;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Denis Lutsenko.
 */
public class InnerTransferCommand implements Command {
    /**
     * This method makes transfer between user accounts.
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        CurrencyConversion conversion = new CurrencyConversion();
        ConditionDao conditionDao = DaoFactory.getInstance().getConditionDao();
        AccountDao accountDao = DaoFactory.getInstance().getAccountDao();

        String fromCard = wrapper.findParameterByName("idFromCard");
        String operationType = wrapper.findParameterByName("operationType");
        String toCard = wrapper.findParameterByName("idToCard");
        String opSumm = wrapper.findParameterByName("operationSumm");

        Calendar cal = Calendar.getInstance();
        Timestamp currDate = new Timestamp(cal.getTimeInMillis());
        int idFromCard = Integer.parseInt(fromCard);
        int idToCard = Integer.parseInt(toCard);
        double operationSumm = Double.parseDouble(opSumm);
        String currencyFrom = accountDao.getCardCurrency(idFromCard);
        String currencyTo = accountDao.getCardCurrency(idToCard);
        double withdrawalPercent = conditionDao.getWithdrawalPercent(idFromCard);

        // calculating new summ. Add to cardTo.
        double summTo = conversion.getCurrencyConverter(currencyFrom, currencyTo, operationSumm);

        // calculating new sum, including withdrawl percent.
        double newSummFrom = conversion.calculateWithdrawalPercent(operationSumm, withdrawalPercent);

        boolean isTransferSuccess = accountDao.doInnerTransfer(idFromCard, operationType, currDate, newSummFrom,
                idToCard,summTo);

        if (isTransferSuccess) {
            return "/jsp/userPages/personalCabinet.jsp";
        } else {
            return "/jsp/reportPages/errorPage.jsp";
        }

    }


}
