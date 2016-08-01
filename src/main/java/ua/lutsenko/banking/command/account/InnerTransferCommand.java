package ua.lutsenko.banking.command.account;



import ua.lutsenko.banking.businesslogic.AccountService;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import java.sql.SQLException;

/**
 * Created by Denis Lutsenko on 7/31/2016.
 */
public class InnerTransferCommand implements Command {
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        AccountService accountService = new AccountService(wrapper);

       // CurrencyConversion conversion = new CurrencyConversion();


//        String fromCard = wrapper.findParameterByName("idFromCard");
//        String operationType = wrapper.findParameterByName("operationType");
//        String toCard = wrapper.findParameterByName("idToCard");
//        String opSumm = wrapper.findParameterByName("operationSumm");
//
//        Calendar cal = Calendar.getInstance();
//        Timestamp currDate = new Timestamp(cal.getTimeInMillis());
//        int idFromCard = Integer.parseInt(fromCard);
//        int idToCard = Integer.parseInt(toCard);
//        double operationSumm = Double.parseDouble(opSumm);
//
//        Operation operationFrom = new Operation();
//        operationFrom.setAccountsId(idFromCard);
//        operationFrom.setOperationType(operationType);
//        operationFrom.setOperationDate(currDate);
//
//        Operation operationTo = new Operation();
//        operationTo.setAccountsId(idToCard);
//        operationTo.setOperationType(operationType);
//        operationTo.setOperationDate(currDate);
//
//        DaoFactory factory = DaoFactory.getInstance();
//        AccountDao accountDao = factory.getAccountsDao();
//
//        String currencyFrom = accountDao.getCardCurrency(idFromCard);
//        String currencyTo = accountDao.getCardCurrency(idToCard);

//        ConditionDao conditionDao = factory.getConditionsDao();
//        double withdrawalPercent = conditionDao.getWithdrawalPercent(idFromCard);
//
//        // calculating new summ. Add to cardTo.
//        double summTo = conversion.getCurrencyConverter(currencyFrom, currencyTo, operationSumm);
//        operationTo.setOperationSum(summTo);
//
//        // calculating new sum, including withdrawl percent.
//        double newSummFrom = conversion.calculateWithdrawalPercent(operationSumm, withdrawalPercent);
//        operationFrom.setOperationSum(newSummFrom);
       // boolean isAdded = accountService.doInnerTransfer();

        if (accountService.doInnerTransfer()) {
           // path = "/jsp/userPages/personalPage.jsp";
           // content.addAttrToSession("msg", MSG);
           // content.addAttrToSession("path", path);
            return "/jsp/userPages/personalCabinet.jsp";
        } else {
           // path = "/jsp/reportPages/errorPage.jsp";
           // content.addAttrToSession("path", path);
            return "/jsp/reportPages/errorPage.jsp";
        }

    }


}
