package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.ConditionDao;
import ua.lutsenko.banking.dao.DaoFactory;
import ua.lutsenko.banking.entity.Condition;
import ua.lutsenko.banking.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis Lutsenko.
 */
public class OperationsHistoryCommand implements Command {
    /**
     * This method shows account history of user.
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {

        Integer userId = ((User) wrapper.findSessionAttrByName("user")).getId();

        ConditionDao conditionDao = DaoFactory.getInstance().getConditionDao();
        List<Condition> accountList = conditionDao.showActiveAccounts(userId);
        wrapper.addNewAttribute("accountList", accountList);

        return "/jsp/accountPages/operationsHistory.jsp";
    }
}
