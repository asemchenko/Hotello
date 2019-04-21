package example.company.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String requestEncoding = "UTF-8";
    private String responseEncoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        requestEncoding = filterConfig.getInitParameter("requestEncoding");
        responseEncoding = filterConfig.getInitParameter("responseEncoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(requestEncoding);
        }
        response.setCharacterEncoding(responseEncoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
