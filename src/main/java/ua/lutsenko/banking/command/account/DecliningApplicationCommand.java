package ua.lutsenko.banking.command.account;



import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko on 8/1/2016.
 */
public class DecliningApplicationCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        AccountService accountService = new AccountService(wrapper);

        boolean isDeleted = accountService.deleteApplication();

        if (isDeleted) {
            return "/jsp/adminPages/adminPersonalCabinet.jsp";
        } else {
            return "/jsp/reportPages/errorPage.jsp";
        }
    }
}