package by.tc.shop.controller.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LocaleFilter implements Filter {
    private String defaultLocale;
    private List<String> supportedLocales;
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String DEFAULT_LOCALE = "defaultLocale";
    private static final String SUPPORTED_LOCALES = "supportedLocales";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        defaultLocale = filterConfig.getInitParameter(DEFAULT_LOCALE);
        supportedLocales = Arrays.asList(filterConfig.getInitParameter(SUPPORTED_LOCALES).split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String locale = (String) session.getAttribute(LOCALE_ATTRIBUTE);
        if (locale == null) {
            String browserLocale = servletRequest.getLocale().getLanguage();
            if (supportedLocales.contains(browserLocale)) {
                session.setAttribute(LOCALE_ATTRIBUTE, browserLocale);
            } else {
                session.setAttribute(LOCALE_ATTRIBUTE, defaultLocale);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        defaultLocale = null;
    }
}
