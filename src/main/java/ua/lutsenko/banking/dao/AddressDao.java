package ua.lutsenko.banking.dao;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Denis Lutsenko.
 */

/**
 * This class works with DataBase queries(Address table) and makes next operations:
 * - check is address exists in table.
 * - insert new address.
 */
public class AddressDao {
    private DataSource ds;
    private static final Logger LOG = Logger.getLogger(AddressDao.class);
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("sqlstatements");

    AddressDao(DataSource ds) {
        this.ds = ds;
    }

    /**
     * This method checks is address exists.
     *
     * @param userId contain ID of user.
     * @return boolean flag.
     */
    public boolean isAddressExist(int userId) {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("IS_ADDRESS_EXIST"));
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            LOG.error("SQL error ", e);
            return false;
        }
    }

    /**
     * This method inserts new address in table.
     *
     * @return Address object.
     * @parameters contains all necessary info to save new address.
     */
    public boolean insertAddress(int userId, String country, String city, String street, String houseNumber) {
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(RESOURCE_BUNDLE.getString("INSERT_ADDRESS"));
            ps.setInt(1, userId);
            ps.setString(2, country);
            ps.setString(3, city);
            ps.setString(4, street);
            ps.setString(5, houseNumber);
            ps.execute();
            return true;
        } catch (SQLException e) {
            LOG.error("SQL error, ", e);
            return false;
        }
    }
}