package ua.lutsenko.banking.dao;

import org.apache.log4j.Logger;
import ua.lutsenko.banking.entity.Account;
import ua.lutsenko.banking.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Denis Lutsenko.
 */

/**
 * * This class works with DataBase queries(Account table) and makes next operations:
 * - do payment.
 * - doInnerTransfer.
 * - getCardCurrency.
 * - updateBalance.
 * - blockAccount.
 * - unblockAccount.
 * - createAccount.
 * - getAccountId.
 * - showBlockedAccounts.
 */
public class AccountDao {
    private DataSource ds;
    private static final Logger LOG = Logger.getLogger(AccountDao.class);
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("sqlstatements");
    private OperationDao operationDao = null;

    public AccountDao(DataSource ds) {
        this.ds = ds;
    }

    /**
     * This is transactional method.  Method makes some payment and
     * save data of payment to operation (history) table.
     *
     * @return boolean flag.
     * @throws SQLException
     * @parameters contains necessary information of card info and payment type.
     */
    public boolean doPayment(int idCard, String operationType, LocalDateTime dateTime, double operationSumm)
            throws SQLException {
        Connection conn = ds.getConnection();
        operationDao = new OperationDao(ds);
        conn.setAutoCommit(false);
        try {
            PreparedStatement psFundsWithdrawal = conn.prepareStatement(RESOURCE_BUNDLE.getString
                    ("WITHDRAWAL_BALANCE"));
            psFundsWithdrawal.setDouble(1, operationSumm);
            psFundsWithdrawal.setInt(2, idCard);
            psFundsWithdrawal.executeUpdate();
            operationDao.insertOperation(conn, idCard, operationType, dateTime, operationSumm);
            psFundsWithdrawal.close();
            return true;
        } catch (SQLException e) {
            LOG.error("DB withdrawal error", e);
            conn.rollback();
            return false;
        } finally {
            conn.commit();
            conn.close();
        }
    }

    /**
     * This is transactional method.
     * Method makes inner transfer between cards. Also this method saves appropriate operations to history table.
     *
     * @return boolean flag.
     * @throws SQLException
     * @parameters contains necessary information of the cards.
     */
    public boolean doInnerTransfer(int idFromCard, String operationType, LocalDateTime currDate, double newSummFrom, int
            idToCard, double summTo) throws SQLException {
        Connection conn = ds.getConnection();
        operationDao = new OperationDao(ds);
        conn.setAutoCommit(false);
        try {
            PreparedStatement psRefillTo = conn.prepareStatement(RESOURCE_BUNDLE.getString("REFILL_BALANCE"));
            psRefillTo.setDouble(1, summTo);
            psRefillTo.setInt(2, idToCard);
            psRefillTo.executeUpdate();
            operationDao.insertOperation(conn, idToCard, operationType, currDate, summTo);

            PreparedStatement psWithDrawalFrom = conn.prepareStatement(RESOURCE_BUNDLE.getString("WITHDRAWAL_BALANCE"));
            psWithDrawalFrom.setDouble(1, newSummFrom);
            psWithDrawalFrom.setInt(2, idFromCard);
            psWithDrawalFrom.executeUpdate();
            operationDao.insertOperation(conn, idFromCard, operationType, currDate, newSummFrom);
            psRefillTo.close();
            psWithDrawalFrom.close();
            return true;
        } catch (SQLException e) {
            LOG.error("inner payment false", e);
            conn.rollback();
            return false;
        } finally {
            conn.commit();
            conn.close();
        }
    }

    /**
     * This method gets card currency  by card id.
     *
     * @param cardId parameter contains selected card id.
     * @return card currency.
     */
    public String getCardCurrency(int cardId) {
        String currency = null;
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("GET_CARD_ID"));
            ps.setInt(1, cardId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                currency = rs.getString("currency");
            }
            ps.close();
            rs.close();
            return currency;
        } catch (SQLException e) {
            LOG.error("SQL error, ", e);
            return null;
        }
    }

    /**
     * This is transactional method.
     * This method updates balance of selected account and save info of refilling to history table.
     *
     * @return boolean flag.
     * @throws SQLException
     * @parameters contains inputted operation sum.
     */
    public boolean updateBalance(int cardId, String operationType, LocalDateTime date, double opSumm) throws
            SQLException {
        operationDao = new OperationDao(ds);
        Connection conn = ds.getConnection();
        conn.setAutoCommit(false);
        try {
            PreparedStatement psAccount = conn.prepareStatement(RESOURCE_BUNDLE.getString("REFILL_BALANCE"));
            psAccount.setDouble(1, opSumm);
            psAccount.setInt(2, cardId);
            psAccount.executeUpdate();
            operationDao.insertOperation(conn, cardId, operationType, date, opSumm);
            psAccount.close();
            return true;
        } catch (SQLException e) {
            LOG.error("DB transaction error", e);
            conn.rollback();
            return false;
        } finally {
            conn.commit();
            conn.close();
        }
    }

    /**
     * This method blocks account by card number.
     *
     * @param cardNumber contain card number.
     * @return boolean flag.
     */
    public boolean blockAccount(String cardNumber) {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("BLOCK_CARD"));
            ps.setString(1, cardNumber);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            LOG.error("SQL exception, ", e);
            return false;
        }
    }

    /**
     * This method builds list of blocked accounts.
     *
     * @return list of blocked accounts.
     */
    public List<Account> showBlockedAccounts() {
        List<Account> blockedAccounts = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("SELECT_BLOCKED_ACCOUNTS"));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String middleName = rs.getString("middle_name");
                String accountCode = rs.getString("account_code");
                double currentBalance = rs.getDouble("current_balance");
                String currency = rs.getString("currency");
                User user = new User(firstName, lastName, middleName);
                Account account = new Account(user, accountCode, currency);
                account.setCurrentBalance(currentBalance);
                blockedAccounts.add(account);
            }
            ps.close();
            rs.close();
            return blockedAccounts;
        } catch (SQLException e) {
            LOG.error("SQL error, ", e);
            return null;
        }
    }

    /**
     * This method unblocks account by accountCode.
     *
     * @param accountCode contain account code.
     * @return boolean flag.
     */
    public boolean unblockAccount(String accountCode) {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("UNBLOCK_CARD"));
            ps.setString(1, accountCode);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            LOG.error("SQL exception, ", e);
            return false;
        }
    }

    /**
     * This is transactional method. Method creates new account and creates account conditions.
     *
     * @return boolean flag.
     * @parameters contains all necessary information to create new account and conditions for current account.
     */
    public boolean createAccount(int userId, String accountCode, LocalDate currDate, String currency, double balance,
                                 double accountWPercent, double monthlyPercent, String deadLine, String type) throws
            SQLException {
        Connection conn = ds.getConnection();
        ConditionDao conditionDao = new ConditionDao(ds);
        conn.setAutoCommit(false);
        try {
            PreparedStatement psAccount = conn.prepareStatement(RESOURCE_BUNDLE.getString("INSERT_ACCOUNT"));
            psAccount.setInt(1, userId);
            psAccount.setString(2, accountCode);
            Date currentDate = Date.valueOf(currDate);
            psAccount.setDate(3, currentDate);
            psAccount.setString(4, currency);
            psAccount.setDouble(5, balance);
            psAccount.setDouble(6, balance);
            psAccount.execute();

            int lastID = getLastInsertId(conn);
            conditionDao.createConditions(conn, lastID, accountWPercent, monthlyPercent, deadLine, type);
            psAccount.close();
            return true;
        } catch (SQLException e) {
            LOG.error(" bad transaction", e);
            conn.rollback();
            return false;
        } finally {
            conn.commit();
            conn.close();
        }
    }

    /**
     * This method gets last inserted ID.
     *
     * @param conn SQL Connection.
     * @return last inserted Id.
     * @throws SQLException
     */
    public int getLastInsertId(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("SELECT_LAST_INS_ID"));
        ResultSet rs = ps.executeQuery();
        int lastId = -1;
        try {
            while (rs.next()) {
                lastId = rs.getInt(1);
            }
            ps.close();
            rs.close();
            return lastId;
        } catch (SQLException e) {
            LOG.error("Bad SQL request ", e);
            return lastId;
        }
    }
}
