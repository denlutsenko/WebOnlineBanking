package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

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
        AccountService accountService = new AccountService(wrapper);
        boolean isAccountAdded = accountService.createAccount();
        boolean isConditionsAdded = accountService.createConditions();

        if (isAccountAdded && isConditionsAdded) {
            System.out.println(wrapper.findSessionAttrByName("applicationId"));
            accountService.updateApplicationStatus("CONFIRMED");
            return "/jsp/adminPages/adminPersonalCabinet.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}