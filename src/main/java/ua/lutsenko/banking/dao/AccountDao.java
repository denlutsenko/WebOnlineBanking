package ua.lutsenko.banking.dao;


import org.apache.log4j.Logger;
import ua.lutsenko.banking.entity.Account;
import ua.lutsenko.banking.entity.User;

import javax.sql.DataSource;
import java.sql.*;
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
    OperationDao operation = null;

    public AccountDao(DataSource ds) {
        this.ds = ds;
    }

    /**
     * This method makes some payment.
     *
     * @return boolean flag.
     * @throws SQLException
     * @parameters contains necessary information of card info and payment type.
     */
    public boolean doPayment(int idCard, String operationType, Timestamp currDate, double operationSumm)
            throws SQLException {
        Connection conn = ds.getConnection();
        conn.setAutoCommit(false);
        try {
            PreparedStatement psFundsWithdrawal = conn.prepareStatement(RESOURCE_BUNDLE.getString
                    ("WITHDRAWAL_BALANCE"));
            psFundsWithdrawal.setDouble(1, operationSumm);
            psFundsWithdrawal.setInt(2, idCard);
            psFundsWithdrawal.executeUpdate();

            PreparedStatement psInsertOp = conn.prepareStatement(RESOURCE_BUNDLE.getString("INSERT_OPERATION"));
            psInsertOp.setInt(1, idCard);
            psInsertOp.setString(2, operationType);
            psInsertOp.setTimestamp(3, currDate);
            psInsertOp.setDouble(4, operationSumm);
            psInsertOp.execute();
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
     * Transaction method.
     * This method makes inner transfer between cards. Also saves operations to history table.
     *
     * @return boolean flag.
     * @throws SQLException
     * @parameters contains necessary information of the first card.
     */
    public boolean doInnerTransfer(int idFromCard, String operationType, Timestamp currDate, double newSummFrom, int
            idToCard, double summTo) throws SQLException {
        Connection conn = ds.getConnection();
        operation = new OperationDao(ds);
        conn.setAutoCommit(false);
        try {
            PreparedStatement psRefillTo = conn.prepareStatement(RESOURCE_BUNDLE.getString("REFILL_BALANCE"));

            psRefillTo.setDouble(1, summTo);
            psRefillTo.setInt(2, idToCard);
            psRefillTo.executeUpdate();

            operation.insertOperation(conn, idToCard, operationType, currDate, summTo);

            PreparedStatement psWithDrawalFrom = conn.prepareStatement(RESOURCE_BUNDLE.getString("WITHDRAWAL_BALANCE"));
            psWithDrawalFrom.setDouble(1, newSummFrom);
            psWithDrawalFrom.setInt(2, idFromCard);
            psWithDrawalFrom.executeUpdate();

            operation.insertOperation(conn, idFromCard, operationType, currDate, newSummFrom);

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
            return currency;
        } catch (SQLException e) {
            LOG.error("SQL error, ", e);
            return null;
        }
    }

    /**
     * Transaction method.
     * This method updates balance of selected account and save info of payment to history table.
     *
     * @return boolean flag.
     * @throws SQLException
     * @parameters contains inputted operation sum.
     */
    public boolean updateBalance(int cardId, String operationType, Timestamp date, double opSumm) throws SQLException {
        operation = new OperationDao(ds);
        Connection conn = ds.getConnection();
        conn.setAutoCommit(false);
        try {
            PreparedStatement psAccount = conn.prepareStatement(RESOURCE_BUNDLE.getString("REFILL_BALANCE"));
            psAccount.setDouble(1, opSumm);
            psAccount.setInt(2, cardId);
            psAccount.executeUpdate();
            operation.insertOperation(conn, cardId, operationType, date, opSumm);
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
            LOG.error("SQL exception, " + e);
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
            return blockedAccounts;
        } catch (SQLException e) {
            LOG.error("SQL error, " + e);
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
     * This method creates new account.
     *
     * @return boolean flag.
     * @parameters contains all necessary information to create new account.
     */
    public boolean createAccount(int userId, String accountCode, Date currDate, String currency, double balance) {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement psAcc = conn.prepareStatement(RESOURCE_BUNDLE.getString("INSERT_ACCOUNT"));
            psAcc.setInt(1, userId);
            psAcc.setString(2, accountCode);
            psAcc.setDate(3, currDate);
            psAcc.setString(4, currency);
            psAcc.setDouble(5, balance);
            psAcc.setDouble(6, balance);
            psAcc.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method gets current account ID.
     *
     * @param accountCode contain account code.
     * @return account code.
     */
    public int getAccountId(String accountCode) {
        int cardId = 0;
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("SELECT_CARD_ID"));
            ps.setString(1, accountCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cardId = rs.getInt("id");
            }
            return cardId;
        } catch (SQLException e) {
            LOG.error("SQL error ", e);
            return 0;
        }
    }
}
