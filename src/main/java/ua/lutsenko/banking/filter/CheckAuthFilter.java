package ua.lutsenko.banking.filter;



import ua.lutsenko.banking.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;

/**
 * Created by Denis Lutsenko.
 */

/**
 * Class filter. Checks client data and provides user access or admin access.
 */
public class CheckAuthFilter implements Filter {
    private String checkAuthFilter;
    private final EnumSet<UrlsStorageUtil> ALL_USERS_URLS = EnumSet.range(UrlsStorageUtil.USER_PAGE_ACCOUNTS,
            UrlsStorageUtil
                    .USER_PAGE_LAYOUT_USER_MENU);
    private final EnumSet<UrlsStorageUtil> ALL_ADMIN_URLS = EnumSet.range(UrlsStorageUtil.ADMIN_PAGE_CABINET,
            UrlsStorageUtil
                    .ADMIN_PAGE_BLOCKED_ACCOUNTS);
    private final EnumSet<UrlsStorageUtil> ALL_URLS = EnumSet.allOf(UrlsStorageUtil.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        checkAuthFilter = filterConfig.getInitParameter("encoding");
    }

    /**
     * This method checks information from servletRequest and provides user or admin access.
     *
     * @see UrlsStorageUtil class
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String path = request.getServletPath();

        if (user == null) {
            for (UrlsStorageUtil urls : ALL_URLS) {
                if (path.equals(urls.getPath()) && (email == null || password == null)) {
                    request.getRequestDispatcher("/jsp/reportPages/error.jsp").forward(request, servletResponse);
                }
            }
            request.getRequestDispatcher(request.getServletPath()).forward(request, servletResponse);
        } else if (!user.isAdmin()) {
            for (UrlsStorageUtil storage : ALL_ADMIN_URLS) {
                if (request.getServletPath().equals(storage.getPath())) {
                    request.getRequestDispatcher("/jsp/reportPages/error.jsp").forward(request, servletResponse);
                    break;
                }
            }
            request.getRequestDispatcher(request.getServletPath()).forward(request, servletResponse);
        } else if (user.isAdmin()) {
            for (UrlsStorageUtil storage : ALL_USERS_URLS) {
                if (request.getServletPath().equals(storage.getPath())) {
                    request.getRequestDispatcher("/jsp/reportPages/error.jsp").forward(request, servletResponse);
                    break;
                }
            }
            request.getRequestDispatcher(request.getServletPath()).forward(request, servletResponse);
        } else {
            request.getRequestDispatcher(request.getServletPath()).forward(request, servletResponse);
        }
    }


    @Override
    public void destroy() {

    }
}
