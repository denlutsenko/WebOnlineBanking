package ua.lutsenko.banking.dao;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by Denis Lutsenko.
 */

/**
 * This class not only makes new Dao of entities also creates new instance (works with config file).
 */
public class DaoFactory {
    private static final Logger LOG = Logger.getLogger(DaoFactory.class);
    private static DaoFactory INSTANCE = null;
    private DataSource ds;

    /**
     * Private constructor. Create object of initial context and init data source.
     * @throws NamingException
     */
    private DaoFactory() throws NamingException {
        InitialContext initContext = new InitialContext();
        ds = (DataSource) initContext.lookup("java:comp/env/jdbc/pbank24");
    }

    /**
     * Synchronized method which create instance of DaoFactory.
     * @return instance
     */
    public static synchronized DaoFactory getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new DaoFactory();
            } catch (NamingException ex) {
                LOG.error("Bad INSTANCE " + ex);
            }
        }
        return INSTANCE;
    }

    public UserDao getUserDao() {
        return new UserDao(ds);
    }

    public AddressDao getAddressDao() {
        return new AddressDao(ds);
    }

    public ApplicationDao getApplicationDao() {
        return new ApplicationDao(ds);
    }

    public AccountDao getAccountDao() {
        return new AccountDao(ds);
    }

    public OperationDao getOperationDao() {
        return new OperationDao(ds);
    }

    public ConditionDao getConditionDao() {
        return new ConditionDao(ds);
    }


}

