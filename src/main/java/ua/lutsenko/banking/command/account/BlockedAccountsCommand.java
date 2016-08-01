package ua.lutsenko.banking.command.account;



import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.entity.Account;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Denis Lutsenko on 8/1/2016.
 */
public class BlockedAccountsCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        AccountService accountService = new AccountService(wrapper);
        List<Account> blockedCardsList = accountService.showBlockedAccounts();
        wrapper.addNewAttributes("blockedCardsList", blockedCardsList);
        return "/jsp/adminPages/blockedAccounts.jsp";
    }
}
