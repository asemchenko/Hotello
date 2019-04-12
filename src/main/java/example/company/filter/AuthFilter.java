package example.company.filter;

import example.company.model.entity.User;
import org.jetbrains.annotations.Nullable;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {
    // TODO реализуй чтение разрешенных URI из файла конфигурации
    private Map<User.UserStatus, List<String>> allowedUri = new HashMap<>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedUri.put(User.UserStatus.ADMIN, Arrays.asList(
                "logout", "profile", "changePassword", "apartment", "findApartment",
                "allOrders", "confirmOrder", "disapproveOrder")
        );
        allowedUri.put(User.UserStatus.CLIENT, Arrays.asList(
                "logout", "profile", "changePassword", "apartment", "findApartment",
                "orders", "booking", "confirmOrder")
        );
        // null represents not authenticated user(just a visitor)
        allowedUri.put(null, Arrays.asList(
                "signIn", "signUp", "apartment", "findApartment")
        );
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        User.UserStatus role = getRole(httpRequest);
        String currentUri = getUri(httpRequest);
        if (allowedUri.get(role).contains(currentUri)) {
            chain.doFilter(request, response);
        } else if (role == null) {
            ((HttpServletResponse) response).sendRedirect("/signIn.jsp");
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Nullable
    private User.UserStatus getRole(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (nonNull(session)) {
            User user = (User) session.getAttribute("user");
            if (nonNull(user)) {
                return user.getStatus();
            }
        }
        return null;
    }

    private String getUri(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        // FIXME magic constant
        return requestURI.replaceAll("/app/", "");
    }

    @Override
    public void destroy() {

    }
}
