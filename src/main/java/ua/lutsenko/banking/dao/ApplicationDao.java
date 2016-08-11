package ua.lutsenko.banking.dao;

import org.apache.log4j.Logger;
import ua.lutsenko.banking.entity.Application;
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
 * This class works with DataBase queries(Application table) and makes next operations:
 * - Insert new application.
 * - get application list.
 * - delete application.
 * - update application status.
 */
public class ApplicationDao {
    private DataSource ds;
    private static final Logger LOG = Logger.getLogger(ApplicationDao.class);
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("sqlstatements");

    ApplicationDao(DataSource ds) {
        this.ds = ds;
    }

    /**
     * This method inserts new application.
     *
     * @return object of application.
     * @parameters contains all necessary data to insert new application.
     */
    public boolean insertApplication(int userId, String accountType, String accountCurrency, LocalDate currDate,
                                     double accountBalance, String accountStatus) {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("INSERT_APPLICATION"));
            ps.setInt(1, userId);
            ps.setString(2, accountType);
            ps.setString(3, accountCurrency);
            Date currentDate = Date.valueOf(currDate);
            ps.setDate(4, currentDate);
            ps.setDouble(5, accountBalance);
            ps.setString(6, accountStatus);
            ps.execute();
            return true;

        } catch (SQLException e) {
            LOG.error("Can't insert application ", e);
            return false;
        }

    }

    /**
     * This method gets all application list from table.
     *
     * @return list of application.
     */
    public List<Application> getApplications() {
        List<Application> applicationsList = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("GET_ALL_APPLICATIONS"));
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
                Date tmpDate = rs.getDate("date");
                LocalDate date = tmpDate.toLocalDate();

                User user = new User(firstName, lastName, middleName);
                user.setId(userId);

                Application application = new Application(user, type, currency, date);
                application.setId(id);
                application.setBalance(balance);
                applicationsList.add(application);
            }
            return applicationsList;
        } catch (SQLException e) {
            LOG.error("Can't get applications ", e);
            return null;
        }
    }

    /**
     * This method updates status of selected application.
     *
     * @param id     contain ID of selected application.
     * @param status contain new value of status.
     * @return boolean flag.
     */
    public boolean updateApplicationStatus(int id, String status) {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("UPDATE_STATUS"));
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(" Can't update application status", e);
            return false;
        }
    }

    /**
     * This method deletes  selected application.
     *
     * @param applicationId contain application id.
     * @return boolean flag.
     */
    public boolean deleteApplication(int applicationId) {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("DELETE_APPLICATION"));
            ps.setInt(1, applicationId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("Can't delete application", e);
            return false;
        }
    }
}
