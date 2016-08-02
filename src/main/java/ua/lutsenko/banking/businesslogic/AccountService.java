package ua.lutsenko.banking.businesslogic;




import ua.lutsenko.banking.command.RequestWrapper;
import ua.lutsenko.banking.currencymanager.CurrencyConversion;
import ua.lutsenko.banking.dao.DaoFactory;
import ua.lutsenko.banking.entity.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Denis Lutsenko.
 */
public class AccountService {

    private DaoFactory daoFactory;
    private RequestWrapper wrapper;

    public AccountService(RequestWrapper wrapper) {
        this.wrapper = wrapper;
        this.daoFactory = DaoFactory.getInstance();
    }

    public List<Condition> getAllConditions() {
        int userId = ((User) wrapper.findSessionAttrByName("user")).getId();
        return daoFactory.getConditionDao().getAccountConditions(userId);
    }

    public List<Condition> getConditions(String cardType) {
        int userId = ((User) wrapper.findSessionAttrByName("user")).getId();
        return daoFactory.getConditionDao().getConditions(userId, cardType);
    }

    public List<Condition> showActiveAccounts() {
        int userId = ((User) wrapper.findSessionAttrByName("user")).getId();
        return daoFactory.getConditionDao().showActiveAccounts(userId);
    }

    public List<Operation> showAccountHistory() {
        String currIdcard = wrapper.findParameterByName("idCard");
        int currCardId = Integer.parseInt(currIdcard);
        return daoFactory.getOperationDao().showAccountHistory(currCardId);
    }

    public boolean doPayment() throws SQLException {
        String currCardId = wrapper.findParameterByName("idFromCard");
        String currOperationSumm = wrapper.findParameterByName("operationSumm");
        String currOperationType = wrapper.findParameterByName("operationType");
        String paymentIdToCard = wrapper.findParameterByName("idToCard");

        String operationType = currOperationType + "" + paymentIdToCard;
        Calendar cal = Calendar.getInstance();
        Timestamp currDate = new Timestamp(cal.getTimeInMillis());
        int idCard = Integer.parseInt(currCardId);
        double operationSumm = Double.parseDouble(currOperationSumm);
        return daoFactory.getAccountDao().doPayment(idCard, operationType, currDate, operationSumm);
    }

    public boolean doInnerTransfer() throws SQLException {
        CurrencyConversion conversion = new CurrencyConversion();
        String fromCard = wrapper.findParameterByName("idFromCard");
        String operationType = wrapper.findParameterByName("operationType");
        String toCard = wrapper.findParameterByName("idToCard");
        String opSumm = wrapper.findParameterByName("operationSumm");

        Calendar cal = Calendar.getInstance();
        Timestamp currDate = new Timestamp(cal.getTimeInMillis());
        int idFromCard = Integer.parseInt(fromCard);
        int idToCard = Integer.parseInt(toCard);
        double operationSumm = Double.parseDouble(opSumm);

        String currencyFrom = daoFactory.getAccountDao().getCardCurrency(idFromCard);
        String currencyTo = daoFactory.getAccountDao().getCardCurrency(idToCard);

        // double withdrawalPercent = conditionDao.getWithdrawalPercent(idFromCard);
        double withdrawalPercent = daoFactory.getConditionDao().getWithdrawalPercent(idFromCard);

        // calculating new summ. Add to cardTo.
        double summTo = conversion.getCurrencyConverter(currencyFrom, currencyTo, operationSumm);

        // calculating new sum, including withdrawl percent.
        double newSummFrom = conversion.calculateWithdrawalPercent(operationSumm, withdrawalPercent);
        return daoFactory.getAccountDao().doInnerTransfer(idFromCard, operationType, currDate, newSummFrom, idToCard,
                summTo);
    }

    public boolean isAddressExist() {
        int userId = ((User) wrapper.findSessionAttrByName("user")).getId();
        return daoFactory.getAddressDao().isAddressExist(userId);
    }

    public boolean insertApplication(){
        final String accountStatus = "PENDING";
        int userId = ((User) wrapper.findSessionAttrByName("user")).getId();
        String accountType = wrapper.findParameterByName("accountType");
        String accountCurrency = wrapper.findParameterByName("accountCurrency");
        Date currDate = Date.valueOf(java.time.LocalDate.now());
        double accountBalance = Double.parseDouble(wrapper.findParameterByName("accountBalance"));
        return daoFactory.getApplicationDao().insertApplication(userId, accountType, accountCurrency, currDate,
                accountBalance, accountStatus);
    }

    public boolean blockAccount(){
        String cardNumber = wrapper.findParameterByName("cardNumber");
        return daoFactory.getAccountDao().blockAccount(cardNumber);
    }

    public boolean updateBalance() throws SQLException {
        String currCardId = wrapper.findParameterByName("currCardId");
        String operationType = wrapper.findParameterByName("operationType");
        String operationSumm = wrapper.findParameterByName("operationSumm");
        int cardId = Integer.parseInt(currCardId);
        double opSumm = Double.parseDouble(operationSumm);
        Calendar cal = Calendar.getInstance();
        Timestamp currDate = new Timestamp(cal.getTimeInMillis());
        return daoFactory.getAccountDao().updateBalance(cardId, operationType, currDate, opSumm);
    }

    public List<Account> showBlockedAccounts() {
        return daoFactory.getAccountDao().showBlockedAccounts();
    }

    public boolean unblockAccount(){
        String accountCode = wrapper.findParameterByName("cardNumber");
        return daoFactory.getAccountDao().unblockAccount(accountCode);
    }

    public List<Application> getApplications(){
        return daoFactory.getApplicationDao().getApplications();
    }


    public boolean createAccount(){
        int userId = Integer.parseInt((String) wrapper.findSessionAttrByName("userId"));
        String currency = (String) wrapper.findSessionAttrByName("currency");
        double balance = Double.parseDouble((String) wrapper.findSessionAttrByName("balance"));
        String accountCode = wrapper.findParameterByName("accountCode");
        Date currDate = Date.valueOf(java.time.LocalDate.now());
        return daoFactory.getAccountDao().createAccount(userId, accountCode, currDate, currency, balance);
    }

    public boolean createConditions() throws SQLException {
        String accountCode = wrapper.findParameterByName("accountCode");
        int accountId = getAccountId(accountCode);
        double accountWPercent = Double.parseDouble(wrapper.findParameterByName("accountWPercent"));
        double monthlyPercent = Double.parseDouble(wrapper.findParameterByName("monthlyPercent"));
        Date accountDeadline = Date.valueOf(wrapper.findParameterByName("accountDeadline"));
        String type = wrapper.findParameterByName("accountType");
        return daoFactory.getConditionDao().createConditions(accountId, accountWPercent, monthlyPercent,
                accountDeadline, type);
    }

    private int getAccountId(String accountCode){
        return daoFactory.getAccountDao().getAccountId(accountCode);
    }

    public boolean updateApplicationStatus(String status) {
        int id = Integer.parseInt((String)wrapper.findSessionAttrByName("applicationId"));
       return  daoFactory.getApplicationDao().updateApplicationStatus(id, status);
    }

    public boolean deleteApplication(){
        int id = Integer.parseInt(wrapper.findParameterByName("applicationId"));
        return daoFactory.getApplicationDao().deleteApplication(id);
    }
}
