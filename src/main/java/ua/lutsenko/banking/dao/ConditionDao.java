package ua.lutsenko.banking.dao;

import org.apache.log4j.Logger;
import ua.lutsenko.banking.entity.Account;
import ua.lutsenko.banking.entity.Condition;
import ua.lutsenko.banking.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Denis Lutsenko.
 */

/**
 * This class works with Condition table and makes next operations:
 * -  get withdrawal percent.
 * - create(add) new conditions to card.
 * - get all account conditions.
 * - get account conditions by single card.
 * - show active accounts.
 */
public class ConditionDao {
    private DataSource ds;
    private static final Logger LOG = Logger.getLogger(ConditionDao.class);
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("sqlstatements");

    public ConditionDao(DataSource ds) {
        this.ds = ds;
    }

    /**
     * This method builds all accounts conditions of current user.
     *
     * @param userId contain user id.
     * @return list of account condition.
     */
    public List<Condition> getAccountConditions(int userId) {
        List<Condition> conditionsList = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("SELECT_USER_CARDS_CONDITIONS"));
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String middleName = rs.getString("middle_name");
                String accountCode = rs.getString("account_code");
                String accountCurrency = rs.getString("currency");
                Date tmpDeadLine = rs.getDate("deadline");
                LocalDate deadLine = tmpDeadLine.toLocalDate();
                String type = rs.getString("type");

                User user = new User(firstName, lastName, middleName);
                Account account = new Account(user, accountCode, accountCurrency);
                Condition condition = new Condition(account, type);

                condition.setDeadLine(deadLine);
                conditionsList.add(condition);
            }
            ps.close();
            rs.close();
            return conditionsList;
        } catch (SQLException e) {
            LOG.error("SQL error, ", e);
            return null;
        }
    }

    /**
     * This method builds all account conditions by account type(CREDIT of DEBIT).
     *
     * @param userId   contain userId.
     * @param cardType contain type of card(CREDIT or DEBIT).
     * @return list of account condition.
     */
    public List<Condition> getConditions(int userId, String cardType) {
        List<Condition> conditionsList = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("SELECT_CARD_CONDITIONS"));
            ps.setInt(1, userId);
            ps.setString(2, cardType);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String middleName = rs.getString("middle_name");
                String accountCode = rs.getString("account_code");
                String currency = rs.getString("currency");
                double currentBalance = rs.getDouble("current_balance");
                double defaultBalance = rs.getDouble("default_balance");
                double percentOfWithdrawal = rs.getDouble("percentOfWithdrawal");
                double monthlyPercent = rs.getDouble("monthlyPercent");
                Date tmpDeadLine = rs.getDate("deadline");
                LocalDate deadLine = tmpDeadLine.toLocalDate();
                String accountType = rs.getString("type");

                User user = new User(firstName, lastName, middleName);
                Account account = new Account(user, accountCode, currency);

                account.setCurrentBalance(currentBalance);
                account.setDefaultBalance(defaultBalance);

                Condition condition = new Condition(account, accountType);
                condition.setPercentOfWithdrawal(percentOfWithdrawal);
                condition.setMonthlyPercent(monthlyPercent);
                condition.setDeadLine(deadLine);
                conditionsList.add(condition);
            }
            ps.close();
            rs.close();
            return conditionsList;
        } catch (SQLException e) {
            LOG.error("SQL error. Can't get conditions, ", e);
            return null;
        }
    }

    /**
     * This method builds list of all active user accounts.
     *
     * @param id parameter contain current user ID.
     * @return list of user active accounts.
     */
    public List<Condition> showActiveAccounts(int id) {
        List<Condition> activeAccounts = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("SELECT_ACTIVE_ACCOUNTS"));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String middleName = rs.getString("middle_name");
                int accountId = rs.getInt("id");
                String accountType = rs.getString("type");
                String accountCode = rs.getString("account_code");
                double currentBalance = rs.getDouble("current_balance");
                String currency = rs.getString("currency");
                Date tmpDeadLine = rs.getDate("deadline");
                LocalDate deadLine = tmpDeadLine.toLocalDate();

                User user = new User(firstName, lastName, middleName);
                Account account = new Account(user, accountCode, currency);
                account.setId(accountId);
                account.setCurrentBalance(currentBalance);

                Condition condition = new Condition(account, accountType);
                condition.setDeadLine(deadLine);
                activeAccounts.add(condition);
            }
            ps.close();
            rs.close();
            return activeAccounts;
        } catch (SQLException e) {
            LOG.error("SQL error, ", e);
            return null;
        }
    }

    /**
     * This method find withdrawal percent and return value.
     *
     * @param accountId parameter contain account id.
     * @return value of withdrawal percent.
     */
    public double getWithdrawalPercent(int accountId) {
        double result = 0.00;
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("GET_WITHDRAWAL_PERCENT"));
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getDouble("percentOfWithdrawal");
            }
        } catch (SQLException e) {
            LOG.error("SQL error, getWithdrawalPercent ", e);
        }
        return result;
    }

    /**
     * This method creates conditions to account.
     *
     * @return boolean flag.
     * @parameters contains all necessary information to make operation.
     */
    public boolean createConditions(Connection conn, int accountId, double accountWPercent, double monthlyPercent,
                                    String accountDeadline, String type) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("INSERT_CONDITIONS"));
            ps.setInt(1, accountId);
            ps.setDouble(2, accountWPercent);
            ps.setDouble(3, monthlyPercent);
            Date accDeadLine = Date.valueOf(accountDeadline);
            ps.setDate(4, accDeadLine);
            ps.setString(5, type);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            LOG.error("Bad SQL request, ", e);
            return false;
        }
    }
}
