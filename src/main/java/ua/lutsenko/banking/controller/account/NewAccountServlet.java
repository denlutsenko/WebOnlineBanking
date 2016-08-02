package ua.lutsenko.banking.controller.account;


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
 * This servlet accepts data from request and call account commands.
 */
public class NewAccountServlet extends HttpServlet {
    private RequestWrapper wrapper;
    private String path;
    private static final Logger LOG = Logger.getLogger(NewAccountServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        Command command = CommandFactory.getInstance().getCommand(request);
        wrapper = new RequestWrapper(request);
        wrapper.addAttrToSession("userId", request.getParameter("userId"));
        wrapper.addAttrToSession("type", request.getParameter("type"));
        wrapper.addAttrToSession("balance", request.getParameter("balance"));
        wrapper.addAttrToSession("currency", request.getParameter("currency"));
        wrapper.addAttrToSession("applicationId", request.getParameter("applicationId"));
        try {
            wrapper.extractParamValues();
            path = command.execute(wrapper);
        } catch (SQLException e) {
            LOG.error("DBError", e);
        }
        response.sendRedirect("/bank24/createNewAccountForm");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        wrapper.insertAttributes();
        request.getRequestDispatcher(path).forward(request, response);
    }
}
