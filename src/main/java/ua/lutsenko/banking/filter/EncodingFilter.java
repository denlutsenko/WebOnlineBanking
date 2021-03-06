package ua.lutsenko.banking.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Denis Lutsenko.
 */

/**
 * This class checks encoding and set it to UTF-8 format.
 */
public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}