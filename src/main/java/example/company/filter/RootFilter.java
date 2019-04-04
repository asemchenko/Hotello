package example.company.filter;

import example.company.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RootFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        if (httpReq.getRequestURI().equals("/")) {
            httpReq.getRequestDispatcher(getForwardURI(httpReq)).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    private String getForwardURI(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            switch (user.getStatus()) {
                case ADMIN:
                    return "/WEB-INF/admin/index.jsp";
                case CLIENT:
                    return "/WEB-INF/user/index.jsp";
            }
        }
        return "/index.jsp";
    }
}
