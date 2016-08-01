package ua.lutsenko.banking.dao;


import org.apache.log4j.Logger;
import ua.lutsenko.banking.entity.Account;
import ua.lutsenko.banking.entity.Operation;
import ua.lutsenko.banking.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Denis Lutsenko on 7/31/2016.
 */
public class OperationDao {
    private DataSource ds;
    private static final Logger LOG = Logger.getLogger(OperationDao.class);
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("sqlstatements");

    OperationDao(DataSource ds) {
        this.ds = ds;
    }


    public List<Operation> showAccountHistory(int currId) throws SQLException {
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

                Timestamp date = rs.getTimestamp("operation_date");
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
            LOG.error("SQL error, " + e);
            return null;
        }
    }

    public boolean insertOperation(Connection conn,int cardId, String operationType, Timestamp currDate, double summ){
        try {
            PreparedStatement psOperation = conn.prepareStatement(RESOURCE_BUNDLE.getString("INSERT_OPERATION"));
            psOperation.setInt(1, cardId);
            psOperation.setString(2, operationType);
            psOperation.setTimestamp(3, currDate);
            psOperation.setDouble(4,summ);
            psOperation.execute();
            return true;
        } catch (SQLException e) {
            LOG.error("SQL error, " + e);
            return false;
        }
    }

}