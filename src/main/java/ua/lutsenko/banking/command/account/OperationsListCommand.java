package ua.lutsenko.banking.command.account;


import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.entity.Condition;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Denis Lutsenko on 7/31/2016.
 */
public class OperationsListCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        AccountService accountService = new AccountService(wrapper);
        List<Condition> accountsList = accountService.showActiveAccounts();
        wrapper.addAttrToSession("accountList", accountsList);

        return "/jsp/accountPages/paymentOperations.jsp";
    }
}
