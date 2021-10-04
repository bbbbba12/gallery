package com.dcinside.gallery.security.passwordAuthFilter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    protected CustomAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/login"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String username = request.getParameter("postId");
        String password = request.getParameter("password");

        if(StringUtils.isEmpty(password)) throw new IllegalArgumentException("password is empty");

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
