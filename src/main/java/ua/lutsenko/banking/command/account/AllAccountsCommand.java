package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.ConditionDao;
import ua.lutsenko.banking.dao.DaoFactory;
import ua.lutsenko.banking.entity.Condition;
import ua.lutsenko.banking.entity.User;

import java.util.List;

/**
 * Created by Denis Lutsenko.
 */
public class AllAccountsCommand implements Command {
    /**
     * This method makes account lists of client and redirect to account page.
     *
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     */
    @Override
    public String execute(RequestWrapper wrapper) {
        int userId = ((User) wrapper.findSessionAttrByName("user")).getId();
        ConditionDao conditionDao = DaoFactory.getInstance().getConditionDao();

        List<Condition> accountConditions = conditionDao.getAccountConditions(userId);
        List<Condition> listCreditConditions = conditionDao.getConditions(userId,"CREDIT");
        List<Condition> listDebitConditions = conditionDao.getConditions(userId,"DEBIT");
        wrapper.addNewAttributes("accountConditions", accountConditions);
        wrapper.addNewAttributes("listCreditConditions", listCreditConditions);
        wrapper.addNewAttributes("listDebitConditions", listDebitConditions);

        return "/jsp/accountPages/allAccounts.jsp";
    }
}
