package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.entity.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis Lutsenko.
 */
public class BlockedAccountsCommand implements Command {
    /**
     * This method makes list of all blocked accounts.
     *
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        AccountService accountService = new AccountService(wrapper);
        List<Account> blockedCardsList = accountService.showBlockedAccounts();
        wrapper.addNewAttributes("blockedCardsList", blockedCardsList);
        return "/jsp/adminPages/blockedAccounts.jsp";
    }
}
