package example.company.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class LangFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        request.setAttribute("lang", getCurrentLanguage(httpRequest));
        chain.doFilter(request, response);
    }

    private String getCurrentLanguage(HttpServletRequest request) {
        Optional<Cookie[]> cookies = Optional.ofNullable(request.getCookies());
        Optional<String> lang = cookies.flatMap(
                cs -> Arrays
                        .stream(cs)
                        .filter(
                                c -> c.getName().equals("lang")
                        ).findAny().map(Cookie::getValue)
        );
        return lang.orElse(getDefaultLanguage(request));
    }

    private String getDefaultLanguage(HttpServletRequest request) {
        return "eng";
//        return request.getLocale().getLanguage();
    }

    @Override
    public void destroy() {

    }
}
