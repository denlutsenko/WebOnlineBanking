package ua.lutsenko.banking.command.account;



import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */
public class WithdrawalCommand implements Command {
    /**
     * This method responsible for withdrawal from account.
     * @param wrapper, wrapper for HttpServletRequest
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        AccountService accountService = new AccountService(wrapper);
        boolean isAdded = accountService.doPayment();

        if (isAdded) {
            return "/jsp/userPages/personalCabinet.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}
