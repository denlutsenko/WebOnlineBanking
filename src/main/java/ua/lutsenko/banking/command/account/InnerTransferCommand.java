package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko on 7/31/2016.
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
        AccountService accountService = new AccountService(wrapper);
        if (accountService.doInnerTransfer()) {
            return "/jsp/userPages/personalCabinet.jsp";
        } else {
            return "/jsp/reportPages/errorPage.jsp";
        }

    }


}
