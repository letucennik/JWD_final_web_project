package by.tc.shop.controller.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;

public class URLFilter implements Filter {
    private static final String FULL_URL_ATTRIBUTE = "fullUrl";
    private static final String URL_CHARSET = "UTF-8";
    private static final String JSP_REQUEST_URI_ATTRIBUTE = "javax.servlet.forward.request_uri";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestUrl = getRequestUrl((HttpServletRequest) servletRequest);
        String encodedURL = URLEncoder.encode(requestUrl, URL_CHARSET);
        servletRequest.setAttribute(FULL_URL_ATTRIBUTE, encodedURL);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private static String getRequestUrl(HttpServletRequest request) {
        String parameters = request.getQueryString();
        String uri = (String) request.getAttribute(JSP_REQUEST_URI_ATTRIBUTE);
        if (uri == null) {
            uri = request.getRequestURI();
        }
        return uri + (parameters == null ? "" : ("?" + parameters));
    }
}
