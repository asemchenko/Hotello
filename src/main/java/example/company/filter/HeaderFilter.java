package example.company.filter;

import example.company.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HeaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setAttribute("headerPath", getHeaderPath((HttpServletRequest) request));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private String getHeaderPath(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            switch (user.getStatus()) {
                case ADMIN:
                    return "/WEB-INF/admin/navbar.jsp";
                case CLIENT:
                    return "/WEB-INF/user/navbar.jsp";
            }
        }
        return "/navbar.jsp";
    }
}
