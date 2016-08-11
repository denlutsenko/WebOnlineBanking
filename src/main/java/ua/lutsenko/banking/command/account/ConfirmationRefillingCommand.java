package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.dao.AccountDao;
import ua.lutsenko.banking.dao.DaoFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Created by Denis Lutsenko.
 */
public class ConfirmationRefillingCommand implements Command {
    /**
     * This method responsible for confirmation refilling account.
     *
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {

        String currCardId = wrapper.findParameterByName("currCardId");
        String operationType = wrapper.findParameterByName("operationType");
        String operationSumm = wrapper.findParameterByName("operationSumm");
        int cardId = Integer.parseInt(currCardId);
        double opSumm = Double.parseDouble(operationSumm);
        LocalDateTime currDate = LocalDateTime.now();

        AccountDao accountDao = DaoFactory.getInstance().getAccountDao();
        boolean isUpdatedBalance = accountDao.updateBalance(cardId, operationType, currDate, opSumm);
        if (isUpdatedBalance) {
            wrapper.addNewAttribute("msg", MSG);
            return "/jsp/userPages/personalCabinet.jsp";
        } else {
            return "/jsp/reportPages/errorPage.jsp";
        }
    }
}