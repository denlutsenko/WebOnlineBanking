package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.entity.Condition;
import ua.lutsenko.banking.entity.Operation;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis Lutsenko.
 */
public class CurrentHistoryCommand implements Command {
    /**
     * This method shows history of selected card.
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {

        AccountService accountService = new AccountService(wrapper);
        List<Condition> accountList = accountService.showActiveAccounts();
        String currIdCard = wrapper.findParameterByName("idCard");
        wrapper.addNewAttributes("currIdcard", currIdCard);

        List<Operation> historyList = accountService.showAccountHistory();
        wrapper.addNewAttributes("accountList", accountList);
        wrapper.addNewAttributes("historyList", historyList);
        return "/jsp/accountPages/currentOperationsHistory.jsp";

    }
}
