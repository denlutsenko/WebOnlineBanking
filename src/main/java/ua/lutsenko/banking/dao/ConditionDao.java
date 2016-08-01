package ua.lutsenko.banking.dao;


import org.apache.log4j.Logger;
import ua.lutsenko.banking.entity.Account;
import ua.lutsenko.banking.entity.Condition;
import ua.lutsenko.banking.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Denis Lutsenko on 7/30/2016.
 */
public class ConditionDao {
    private DataSource ds;
    private static final Logger LOG = Logger.getLogger(ConditionDao.class);
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("sqlstatements");

    ConditionDao(DataSource ds) {
        this.ds = ds;
    }


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
                Date deadLine = rs.getDate("deadline");
                String type = rs.getString("type");
                User user = new User(firstName, lastName, middleName);
                Account account = new Account(user, accountCode, accountCurrency);
                Condition condition = new Condition(account, type);
                condition.setDeadLine(deadLine);
                conditionsList.add(condition);
            }
            return conditionsList;
        } catch (SQLException e) {
            LOG.error("SQL error, ", e);
            return null;
        }
    }


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
                Date deadLine = rs.getDate("deadline");
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
            return conditionsList;
        } catch (SQLException e) {
            LOG.error("SQL error. Can't get conditions, ", e);
            return null; // TO DO!!!
        }
    }


    public List<Condition> showActiveAccounts(int id){
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
                Date deadLine = rs.getDate("deadline");


                User user = new User(firstName, lastName, middleName);

                Account account = new Account(user, accountCode, currency);
                account.setId(accountId);
                account.setCurrentBalance(currentBalance);

                Condition condition = new Condition(account, accountType);
                condition.setDeadLine(deadLine);

                activeAccounts.add(condition);
            }
            return activeAccounts;
        } catch (SQLException e) {
            LOG.error("SQL error, " + e);
            return null;
        }
    }


    public double getWithdrawalPercent(int accountId) throws SQLException {
        double result = 0.00;
        try (Connection conn = ds.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("GET_WITHDRAWAL_PERCENT"));
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getDouble("percentOfWithdrawal");
            }
        } catch (SQLException e) {
            LOG.error("SQL error, " + e);
        }
        return result;
    }

    public boolean createConditions(int accountId,  double accountWPercent,  double monthlyPercent, Date accountDeadline,  String type) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("INSERT_CONDITIONS"));
            ps.setInt(1, accountId);
            ps.setDouble(2, accountWPercent);
            ps.setDouble(3, monthlyPercent);
            ps.setDate(4, accountDeadline);
            ps.setString(5, type);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
