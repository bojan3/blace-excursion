package com.blace.excursion.service;

import com.blace.excursion.dto.AuthenticationRequest;
import com.blace.excursion.dto.UserTokenState;

public interface AuthenticationService {

    UserTokenState loginUser(AuthenticationRequest authenticationRequest);

}
