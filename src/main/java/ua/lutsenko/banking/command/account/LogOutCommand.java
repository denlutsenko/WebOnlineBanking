package ua.lutsenko.banking.command.account;

import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.RequestWrapper;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */
public class LogOutCommand implements Command {
    /**
     * This method responsible for destroying of session.
     * @param wrapper wrapper for HttpServletRequest.
     * @return path to load a new jsp page.
     * @throws SQLException
     */
    @Override
    public String execute(RequestWrapper wrapper) throws SQLException {
        HttpSession session = wrapper.getSession();
        session.invalidate();
        return "/jsp/index.jsp";
    }
}
