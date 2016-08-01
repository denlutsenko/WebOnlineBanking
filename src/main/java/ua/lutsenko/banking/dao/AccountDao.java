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
 * Created by Denis Lutsenko on 7/30/2016.
 */
public class AccountDao {
    private DataSource ds;
    private static final Logger LOG = Logger.getLogger(AccountDao.class);
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("sqlstatements");
    OperationDao operation = null;

    public AccountDao(DataSource ds) {
        this.ds = ds;
    }


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


    public String getCardCurrency(int cardId) throws SQLException {
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
            LOG.error("SQL error, " + e);
            return null;
        }

    }


    public boolean updateBalance(int cardId, String operationType, Timestamp date, double opSumm) throws SQLException {
        operation = new OperationDao(ds);
        Connection conn = ds.getConnection();
        conn.setAutoCommit(false);
        try {
            PreparedStatement psAccount = conn.prepareStatement(RESOURCE_BUNDLE.getString("REFILL_BALANCE"));
            System.out.println(opSumm + "   opSumm 111111111111111111111");
            psAccount.setDouble(1, opSumm);
            System.out.println(cardId + " cardId  22222222222222");
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


    public boolean blockAccount(String cardNumber) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("BLOCK_CARD"));
            ps.setString(1, cardNumber);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            LOG.error("SQL exception, " + e);
            return false;
        }
    }

    public List<Account> showBlockedAccounts() throws SQLException {
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


    public boolean unblockAccount(String accountCode) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("UNBLOCK_CARD"));
            ps.setString(1, accountCode);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            LOG.error("SQL exception, " + e);
            return false;
        }
    }


    public boolean createAccount(int userId, String accountCode, Date currDate, String currency, double
            balance) throws SQLException {
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



    public int getAccountId(String accountCode) throws SQLException {
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
            LOG.error("SQL error, " + e);
            return 0;
        }
    }

//    private int getLastInsertId() throws SQLException {
//        Connection conn = ds.getConnection();
//        try (Statement statement = conn.createStatement()) {
//            try (ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()")) {
//                return resultSet.getInt(1);
//            }
//        }
//    }

}
