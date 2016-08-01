package ua.lutsenko.banking.command.account;


import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.entity.Condition;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Denis Lutsenko on 8/1/2016.
 */
public class BlockAccountCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        AccountService accountService = new AccountService(wrapper);
        List<Condition> activeAccounts = accountService.showActiveAccounts();
        if (activeAccounts != null) {
            wrapper.addNewAttributes("activeAccounts", activeAccounts);
            return "/jsp/accountPages/blockCardForm.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}