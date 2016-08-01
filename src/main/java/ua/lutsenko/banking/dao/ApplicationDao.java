package ua.lutsenko.banking.dao;


import org.apache.log4j.Logger;
import ua.lutsenko.banking.entity.Application;
import ua.lutsenko.banking.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Denis Lutsenko on 7/25/2016.
 */
public class ApplicationDao {
    private DataSource ds;
    private static final Logger logger = Logger.getLogger(ApplicationDao.class);
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlstatements");

    ApplicationDao(DataSource ds) {
        this.ds = ds;
    }


    public boolean insertApplication(int userId, String accountType, String accountCurrency, Date currDate,
                                     double accountBalance, String accountStatus) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(resourceBundle.getString("INSERT_APPLICATION"));
            ps.setInt(1, userId);
            ps.setString(2, accountType);
            ps.setString(3, accountCurrency);
            ps.setDate(4, currDate);
            ps.setDouble(5, accountBalance);
            ps.setString(6, accountStatus);
            ps.execute();
            return true;

        } catch (SQLException e) {
            logger.error("SQL error, " + e);
            return false;
        }

    }

    public List<Application> getApplications() throws SQLException {
        List<Application> applicationsList = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(resourceBundle.getString("GET_ALL_APPLICATIONS"));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                String middleName = rs.getString("middle_name");
                String type = rs.getString("type");
                String currency = rs.getString("currency");
                double balance = rs.getDouble("balance");
                Date date = rs.getDate("date");

                User user = new User(firstName, lastName, middleName);
                user.setId(userId);

                Application application = new Application(user, type, currency, date);
                application.setId(id);
                application.setBalance(balance);

                applicationsList.add(application);
            }
            return applicationsList;
        } catch (SQLException e) {
            logger.error("SQL error, " + e);
            return null;
        }
    }


    public boolean updateApplicationStatus(int id, String status){
        try(Connection conn = ds.getConnection()){
            PreparedStatement ps = conn.prepareStatement(resourceBundle.getString("UPDATE_STATUS"));
            ps.setString(1, status);
            ps.setInt(2, id);
           ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteApplication(int applicationId) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(resourceBundle.getString("DELETE_APPLICATION"));
            ps.setInt(1, applicationId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
