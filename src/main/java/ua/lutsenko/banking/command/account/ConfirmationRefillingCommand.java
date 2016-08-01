package ua.lutsenko.banking.command.account;



import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko on 8/1/2016.
 */
public class ConfirmationRefillingCommand implements Command {

    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        AccountService accountService = new AccountService(wrapper);

        if (accountService.updateBalance()) {
            return "/jsp/userPages/personalCabinet.jsp";
        } else {
            return "/jsp/reportPages/errorPage.jsp";
        }
    }
}