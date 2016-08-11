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
public class PaymentCommand implements Command {
    /**
     * This method makes some payment from client.
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {

        String currCardId = wrapper.findParameterByName("idFromCard");
        String currOperationSumm = wrapper.findParameterByName("operationSumm");
        String currOperationType = wrapper.findParameterByName("operationType");
        String paymentIdToCard = wrapper.findParameterByName("idToCard");
        String operationType = currOperationType + "" + paymentIdToCard;
        int idCard = Integer.parseInt(currCardId);
        double operationSumm = Double.parseDouble(currOperationSumm);
        LocalDateTime currDate = LocalDateTime.now();

        AccountDao accountDao = DaoFactory.getInstance().getAccountDao();
        boolean isAdded = accountDao.doPayment(idCard, operationType, currDate, operationSumm);
        if (isAdded) {
            wrapper.addNewAttribute("msg", MSG);
            return "/jsp/userPages/personalCabinet.jsp";
        } else {
            return "/jsp/reportPages/error.jsp";
        }
    }
}