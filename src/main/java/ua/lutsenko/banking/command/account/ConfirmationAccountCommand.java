package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.AccountDao;
import ua.lutsenko.banking.dao.ApplicationDao;
import ua.lutsenko.banking.dao.DaoFactory;

import java.sql.SQLException;
import java.time.LocalDate;

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
        int applicationId = Integer.parseInt((String) wrapper.findSessionAttrByName("applicationId"));
        double balance = Double.parseDouble((String) wrapper.findSessionAttrByName("balance"));
        double accountWPercent = Double.parseDouble(wrapper.findParameterByName("accountWPercent"));
        double monthlyPercent = Double.parseDouble(wrapper.findParameterByName("monthlyPercent"));
        String currency = (String) wrapper.findSessionAttrByName("currency");
        String accountCode = wrapper.findParameterByName("accountCode");
        String deadLine = wrapper.findParameterByName("accountDeadline");
        String type = wrapper.findParameterByName("accountType");
        LocalDate currentDate = LocalDate.now();

        AccountDao accountDao = DaoFactory.getInstance().getAccountDao();
        boolean isAccountCreated = accountDao.createAccount(userId, accountCode, currentDate, currency, balance,
                accountWPercent, monthlyPercent, deadLine, type);

        ApplicationDao applicationDao = DaoFactory.getInstance().getApplicationDao();
        if (isAccountCreated) {
            applicationDao.updateApplicationStatus(applicationId, "CONFIRMED");
            wrapper.addNewAttribute("msg", MSG);
            return "/jsp/adminPages/adminPersonalCabinet.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}