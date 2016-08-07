package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.AccountDao;
import ua.lutsenko.banking.dao.ApplicationDao;
import ua.lutsenko.banking.dao.ConditionDao;
import ua.lutsenko.banking.dao.DaoFactory;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */
public class ConfirmationAccountCommand implements Command {
    /**
     * This method receives all inputted data of new account and save it to the data base.
     *
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {

        int userId = Integer.parseInt((String) wrapper.findSessionAttrByName("userId"));
        String currency = (String) wrapper.findSessionAttrByName("currency");
        double balance = Double.parseDouble((String) wrapper.findSessionAttrByName("balance"));
        String accountCode = wrapper.findParameterByName("accountCode");
        Date currDate = Date.valueOf(java.time.LocalDate.now());

        AccountDao accountDao = DaoFactory.getInstance().getAccountDao();
        boolean isAccountAdded = accountDao.createAccount(userId, accountCode, currDate, currency, balance);

        int accountId = accountDao.getAccountId(accountCode);
        double accountWPercent = Double.parseDouble(wrapper.findParameterByName("accountWPercent"));
        double monthlyPercent = Double.parseDouble(wrapper.findParameterByName("monthlyPercent"));
        Date accountDeadline = Date.valueOf(wrapper.findParameterByName("accountDeadline"));
        String type = wrapper.findParameterByName("accountType");
        int applicationId = Integer.parseInt((String) wrapper.findSessionAttrByName("applicationId"));

        ConditionDao conditionDao = DaoFactory.getInstance().getConditionDao();
        boolean isConditionsAdded = conditionDao.createConditions(accountId, accountWPercent, monthlyPercent,
                accountDeadline, type);

        ApplicationDao applicationDao = DaoFactory.getInstance().getApplicationDao();

        if (isAccountAdded && isConditionsAdded) {
            applicationDao.updateApplicationStatus(applicationId, "CONFIRMED");
            return "/jsp/adminPages/adminPersonalCabinet.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}