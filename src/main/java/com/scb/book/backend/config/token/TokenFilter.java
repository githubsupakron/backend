package com.scb.book.backend.config.token;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.scb.book.backend.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TokenFilter extends GenericFilter {

    private final TokenService tokenService;

    public TokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // check all request is have token
        // ServletRequest convert to  HttpServletRequest
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");

        if (ObjectUtils.isEmpty(authorization)) {
            chain.doFilter(request, response);
            return;
        }

        //check is Bearer
        if (!authorization.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        //Decoded for chcek token is ture and notexpile
        String token = authorization.substring(7);
        DecodedJWT decodedJWT = tokenService.verifyToken(token);
        if (Objects.isNull(decodedJWT)) {
            chain.doFilter(request, response);
            return;
        }

        String principal = decodedJWT.getClaim("principal").asString();
        String role = decodedJWT.getClaim("role").asString();

        List<GrantedAuthority> authoriList = new ArrayList<>();
        authoriList.add(new SimpleGrantedAuthority(role));

        // Authentication by id pass and role
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(principal, "(protected)", authoriList);

        // set AuthenticationToken to SecurityContext
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(usernamePasswordAuthenticationToken);

        //return response
        chain.doFilter(request, response);
    }
}
