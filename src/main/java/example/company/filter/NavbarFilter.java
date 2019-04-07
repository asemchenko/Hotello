package example.company.filter;

import example.company.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class NavbarFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String navbarPath = getNavbarPath((HttpServletRequest) request);
        if (Objects.nonNull(navbarPath)) {
            request.setAttribute("navbarPath", navbarPath);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private String getNavbarPath(HttpServletRequest request) {
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
        return null;
    }
}
