package ua.lutsenko.banking.command.account;



import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko on 8/1/2016.
 */
public class ConfirmationAccountCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        AccountService accountService = new AccountService(wrapper);
        boolean isAccountAdded = accountService.createAccount();
        boolean isConditionsAdded = accountService.createConditions();

        if (isAccountAdded && isConditionsAdded) {
            System.out.println(wrapper.findSessionAttrByName("applicationId"));
            boolean isAdded = accountService.updateApplicationStatus("CONFIRMED");
            return "/jsp/adminPages/adminPersonalCabinet.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}