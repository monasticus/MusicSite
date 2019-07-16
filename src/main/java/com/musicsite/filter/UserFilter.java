package com.musicsite.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserFilter", urlPatterns = {"/login", "/register"})
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (!(req instanceof HttpServletRequest)) {
            chain.doFilter(req, resp);
            return;
        }


        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        boolean loggedIn = session.getAttribute("loggedUserId") != null;

        if (loggedIn) {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect("/");
            return;
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
