package com.musicsite.filter;

import com.musicsite.entity.User;
import com.musicsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminAuthenticationFilter",
        urlPatterns = {
                "/adm/*",
                "/adm/add/*",
                "/adm/propositions/*/*"})

public class AdminAuthenticationFilter implements Filter {


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
            boolean admin = (boolean) session.getAttribute("capo");

            if (admin) {
                chain.doFilter(req, resp);
                return;
            }

            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "You are not authorized to see this page!");
            return;
        }


        HttpServletResponse response = (HttpServletResponse) resp;
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "You are not authorized to see this page!");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
