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
public class AllAccountsCommand implements Command {
    /**
     * This method makes account lists of client and redirect to account page.
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     */
    @Override
    public String execute(RequestWrapper wrapper){
        AccountService accountService = new AccountService(wrapper);
        List<Condition> accountConditions = accountService.getAllConditions();
        List<Condition> listCreditConditions = accountService.getConditions("CREDIT");
        List<Condition> listDebitConditions = accountService.getConditions("DEBIT");
        wrapper.addNewAttributes("accountConditions", accountConditions);
        wrapper.addNewAttributes("listCreditConditions", listCreditConditions);
        wrapper.addNewAttributes("listDebitConditions", listDebitConditions);

        return "/jsp/accountPages/allAccounts.jsp";
    }
}
