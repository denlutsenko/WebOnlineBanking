package ua.lutsenko.banking.dao;

import org.apache.log4j.Logger;
import ua.lutsenko.banking.entity.Account;
import ua.lutsenko.banking.entity.Operation;
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
 * This class works with Operation table and makes next operations:
 * - insert operation.
 * - show account history.
 */
public class OperationDao {
    private DataSource ds;
    private static final Logger LOG = Logger.getLogger(OperationDao.class);
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("sqlstatements");

    OperationDao(DataSource ds) {
        this.ds = ds;
    }

    /**
     * This method makes history list of current account.
     *
     * @param currId parameter contain current user id.
     * @return list of accounts by current user.
     */
    public List<Operation> showAccountHistory(int currId) {
        List<Operation> historyList = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("SELECT_ACCOUNT_HISTORY"));
            ps.setInt(1, currId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String middleName = rs.getString("middle_name");
                String accountCode = rs.getString("account_code");
                String currency = rs.getString("currency");
                Timestamp tmpDate = rs.getTimestamp("operation_date");
                LocalDate date = LocalDate.from(tmpDate.toLocalDateTime());
                String type = rs.getString("operation_type");
                double summ = rs.getDouble("operation_summ");

                User user = new User(firstName, lastName, middleName);
                Account account = new Account(user, accountCode, currency);
                Operation operation = new Operation(account);
                operation.setOperationDate(date);
                operation.setOperationType(type);
                operation.setOperationSumm(summ);
                historyList.add(operation);
            }
            return historyList;
        } catch (SQLException e) {
            LOG.error("SQL error, ", e);
            return null;
        }
    }

    /**
     * This method inserts operation by any transfer. Also this method a part of transaction (@see AccountDao class).
     *
     * @param conn parameter takes connection from transaction method from AccountDao.
     * @parameters  contains all necessary info to save data into table.
     * @return boolean flag.
     */
    public boolean insertOperation(Connection conn, int cardId, String operationType, LocalDateTime currDate, double summ){
        try {
            PreparedStatement psOperation = conn.prepareStatement(RESOURCE_BUNDLE.getString("INSERT_OPERATION"));
            psOperation.setInt(1, cardId);
            psOperation.setString(2, operationType);
            Timestamp currentDate = Timestamp.valueOf(currDate);
            psOperation.setTimestamp(3, currentDate);
            psOperation.setDouble(4,summ);
            psOperation.execute();
            return true;
        } catch (SQLException e) {
            LOG.error("SQL error, ", e);
            return false;
        }
    }

    public boolean deleteOperation(int id) {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("DELETE_OPERATION"));
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("Can't delete operation", e);
            return false;
        }
    }
}
