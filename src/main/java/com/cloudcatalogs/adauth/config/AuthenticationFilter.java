package com.cloudcatalogs.adauth.config;

import com.cloudcatalogs.userauthentication.SourceRequestFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created on 9/24/18.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class AuthenticationFilter implements SourceRequestFilter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = ((HttpServletRequest) request);
        chain.doFilter(req, response);

    }
    @Override
    public void destroy() {

    }
}
