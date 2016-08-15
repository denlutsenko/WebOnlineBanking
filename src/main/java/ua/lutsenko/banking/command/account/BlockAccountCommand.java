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
public class BlockAccountCommand implements Command {

    /**
     * This method responsible for locking account of user.
     *
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     */
    @Override
    public String execute(RequestWrapper wrapper) {

        Integer userId = ((User) wrapper.findSessionAttrByName("user")).getId();

        ConditionDao conditionDao = DaoFactory.getInstance().getConditionDao();
        List<Condition> activeAccounts = conditionDao.showActiveAccounts(userId);
        if (activeAccounts != null) {
            wrapper.addNewAttribute("activeAccounts", activeAccounts);
            return "/jsp/accountPages/blockCardForm.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}