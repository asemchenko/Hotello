package example.company.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.IllformedLocaleException;
import java.util.Locale;
import java.util.Optional;

public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        request.setAttribute("locale", getCurrentLocale(httpRequest));
        chain.doFilter(request, response);
    }

    private Locale getCurrentLocale(HttpServletRequest request) {
        Optional<Cookie[]> cookies = Optional.ofNullable(request.getCookies());
        Optional<String> localeTag = cookies.flatMap(
                cs -> Arrays
                        .stream(cs)
                        .filter(
                                c -> c.getName().equals("locale")
                        ).findAny().map(Cookie::getValue)
        );
        Optional<Locale> locale = null;
        try {
            locale = localeTag.map(
                    s -> new Locale.Builder().setLanguage(s.split("-")[0]).setRegion(s.split("-")[1]).build()
            );
        } catch (IllformedLocaleException e) {
            return Locale.getDefault();
        }
        return locale.orElse(Locale.getDefault());
    }

    @Override
    public void destroy() {

    }
}
