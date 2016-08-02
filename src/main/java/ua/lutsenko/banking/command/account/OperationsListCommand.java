package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.entity.Condition;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis Lutsenko.
 */
public class OperationsListCommand implements Command {
    /**
     * This method makes list of active accounts of user and redirect to page of operations with accounts.
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        AccountService accountService = new AccountService(wrapper);
        List<Condition> accountsList = accountService.showActiveAccounts();
        wrapper.addAttrToSession("accountList", accountsList);
        return "/jsp/accountPages/paymentOperations.jsp";
    }
}
