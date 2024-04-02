package com.cars.comparison.serivces.authentication;


import com.cars.comparison.dto.authentication.JwtAuthenticationResponse;
import com.cars.comparison.dto.authentication.SignInRequest;
import com.cars.comparison.dto.authentication.SignUpRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}