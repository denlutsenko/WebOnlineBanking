package ua.lutsenko.banking.command;

import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Denis Lutsenko.
 */
public interface Command {
     String execute(RequestWrapper wrapper) throws SQLException;
}