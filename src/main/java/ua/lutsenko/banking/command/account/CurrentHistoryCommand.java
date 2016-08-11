package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.ConditionDao;
import ua.lutsenko.banking.dao.DaoFactory;
import ua.lutsenko.banking.dao.OperationDao;
import ua.lutsenko.banking.entity.Condition;
import ua.lutsenko.banking.entity.Operation;
import ua.lutsenko.banking.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis Lutsenko.
 */
public class CurrentHistoryCommand implements Command {
    /**
     * This method shows history of selected card.
     *
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {

        String idCard = wrapper.findParameterByName("idCard");
        int currCardId = Integer.parseInt(idCard);
        int userId = ((User) wrapper.findSessionAttrByName("user")).getId();

        OperationDao operationDao = DaoFactory.getInstance().getOperationDao();
        ConditionDao conditionDao = DaoFactory.getInstance().getConditionDao();
        List<Operation> historyList = operationDao.showAccountHistory(currCardId);
        List<Condition> accountList = conditionDao.showActiveAccounts(userId);

        wrapper.addNewAttribute("currIdcard", idCard);
        wrapper.addNewAttribute("accountList", accountList);
        wrapper.addNewAttribute("historyList", historyList);

        return "/jsp/accountPages/currentOperationsHistory.jsp";

    }
}
