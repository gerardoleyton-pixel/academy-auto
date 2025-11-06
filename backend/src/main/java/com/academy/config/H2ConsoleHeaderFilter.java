package com.academy.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Ensures H2 console pages can be framed by setting a permissive X-Frame-Options for /h2-console paths.
 */
@Component
@Order(0)
public class H2ConsoleHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        filterChain.doFilter(request, response);

        String uri = request.getRequestURI();
        if (uri != null && uri.startsWith("/h2-console")) {
            // allow same-origin framing for the H2 console
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
        }
    }
}
