package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.AccountDao;
import ua.lutsenko.banking.dao.DaoFactory;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */
public class ConfirmationBlockingAccountCommand implements Command {
    /**
     * This method locking user account.
     *
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        String cardNumber = wrapper.findParameterByName("cardNumber");
        AccountDao accountDao = DaoFactory.getInstance().getAccountDao();

        boolean isBlocked = accountDao.blockAccount(cardNumber);

        if (isBlocked) {
            return "/jsp/userPages/personalCabinet.jsp";
        } else {
            return "/jsp/reportPages/errorPage.jsp";
        }
    }
}