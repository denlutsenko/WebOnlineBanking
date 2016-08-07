package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.ApplicationDao;
import ua.lutsenko.banking.dao.DaoFactory;
import ua.lutsenko.banking.entity.User;

import java.sql.Date;

/**
 * Created by Denis Lutsenko.
 */
public class ConfirmationApplicationCommand implements Command {
    /**
     * This method receives all necessary data and approve opening new account for client.
     *
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     */
    @Override
    public String execute(RequestWrapper wrapper) {
        final String accountStatus = "PENDING";
        int userId = ((User) wrapper.findSessionAttrByName("user")).getId();
        String accountType = wrapper.findParameterByName("accountType");
        String accountCurrency = wrapper.findParameterByName("accountCurrency");
        Date currDate = Date.valueOf(java.time.LocalDate.now());
        double accountBalance = Double.parseDouble(wrapper.findParameterByName("accountBalance"));
        ApplicationDao applicationDao = DaoFactory.getInstance().getApplicationDao();

        boolean isInsertedApplication = applicationDao.insertApplication(userId, accountType, accountCurrency,
                currDate, accountBalance, accountStatus);

        if (isInsertedApplication) {
            return "/jsp/userPages/personalCabinet.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}