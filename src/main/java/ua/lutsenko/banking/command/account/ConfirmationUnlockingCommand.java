package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.AccountDao;
import ua.lutsenko.banking.dao.DaoFactory;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */
public class ConfirmationUnlockingCommand implements Command {
    /**
     * This method unlocking user account.
     *
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {

        String accountCode = wrapper.findParameterByName("cardNumber");
        AccountDao accountDao = DaoFactory.getInstance().getAccountDao();
        boolean isUnblocked = accountDao.unblockAccount(accountCode);
        if (isUnblocked) {
            wrapper.addNewAttribute("msg", MSG);
            return "/jsp/adminPages/adminPersonalCabinet.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}