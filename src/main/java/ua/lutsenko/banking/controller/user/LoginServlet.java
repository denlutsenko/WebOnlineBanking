package ua.lutsenko.banking.controller.user;



import org.apache.log4j.Logger;
import ua.lutsenko.banking.command.Command;
import ua.lutsenko.banking.command.CommandFactory;
import ua.lutsenko.banking.command.RequestWrapper;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Denis Lutsenko.
 */

/**
 * This servlet accepts user dara from request and call user commands.
 */
public class LoginServlet extends HttpServlet {
    private RequestWrapper wrapper;
    private String path;
    private static final Logger LOG = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        wrapper = new RequestWrapper(request);
        wrapper.addParameter("email", request.getParameter("email"));
        wrapper.addParameter("password", request.getParameter("password"));
        wrapper.addParameter("cardNumber", request.getParameter("cardNumber"));
        Command command = CommandFactory.getInstance().getCommand(request);
        try {
            path = command.execute(wrapper);
        } catch (SQLException e) {
            LOG.error("DBError", e);
        }
        response.sendRedirect("/bank24/personalCabinet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        wrapper.insertAttributes();
        request.getRequestDispatcher(path).forward(request, response);
    }
}
