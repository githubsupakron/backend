package com.scb.book.backend.config.token;

import com.scb.book.backend.service.TokenService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TokenFilterConfiguerer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenService tokenService;

    public TokenFilterConfiguerer(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        TokenFilter filter = new TokenFilter(tokenService);
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        super.configure(builder);
    }
}
