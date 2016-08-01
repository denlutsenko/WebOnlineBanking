package ua.lutsenko.banking.command.account;




import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.entity.Condition;
import ua.lutsenko.banking.entity.Operation;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Denis Lutsenko on 7/26/2016.
 */
public class CurrentHistoryCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {

        AccountService accountService = new AccountService(wrapper);
        List<Condition> accountList = accountService.showActiveAccounts();
        String currIdcard = wrapper.findParameterByName("idCard");
        wrapper.addNewAttributes("currIdcard", currIdcard);

        List<Operation> historyList = accountService.showAccountHistory();
        wrapper.addNewAttributes("accountList", accountList);
        wrapper.addNewAttributes("historyList", historyList);
        return "/jsp/accountPages/currentOperationsHistory.jsp";

    }
}
