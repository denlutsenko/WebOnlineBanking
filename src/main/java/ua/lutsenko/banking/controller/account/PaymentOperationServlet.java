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
public class PaymentOperationServlet extends HttpServlet {
    private RequestWrapper wrapper;
    private String path;
    private static final Logger LOG = Logger.getLogger(PaymentOperationServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        wrapper = new RequestWrapper(request);

        wrapper.addParameter("idFromCard", request.getParameter("idFromCard"));
        wrapper.addParameter("idToCard", request.getParameter("idToCard"));
        wrapper.addParameter("operationType", request.getParameter("operationType"));
        wrapper.addParameter("operationSumm", request.getParameter("operationSumm"));
        Command command = CommandFactory.getInstance().getCommand(request);

        try {
            wrapper.extractParamValues();
            path = command.execute(wrapper);
            System.out.println(path);
        } catch (SQLException e) {
            LOG.error("DBError", e);
        }
        response.sendRedirect("/bank24/PaymentOperations");


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        wrapper.insertAttributes();
        request.getRequestDispatcher(path).forward(request, response);
    }
}
