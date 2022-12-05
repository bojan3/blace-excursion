package com.blace.excursion.service.impl;

import com.blace.excursion.dto.AuthenticationRequest;
import com.blace.excursion.dto.UserTokenState;
import com.blace.excursion.model.User;
import com.blace.excursion.service.AuthenticationService;
import com.blace.excursion.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private AuthenticationManager authenticationManager;

    private TokenUtils tokenUtils;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, TokenUtils tokenUtils) {
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public UserTokenState loginUser(AuthenticationRequest authenticationRequest) throws AuthenticationException {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        String JWT = tokenUtils.generateToken(user);

        return new UserTokenState(JWT, tokenUtils.getExpiredIn());
    }

}
