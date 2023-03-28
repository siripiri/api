package com.transport.sabi.api.controllers.auth;


import com.transport.sabi.api.config.JwtService;
import com.transport.sabi.api.domain.Role;
import com.transport.sabi.api.domain.UserDetail;
import com.transport.sabi.api.repository.UserDetailRepository;
import com.transport.sabi.api.services.exception.BadRequestException;
import com.transport.sabi.api.v1.model.authDto.AuthenticationRequest;
import com.transport.sabi.api.v1.model.authDto.AuthenticationResponse;
import com.transport.sabi.api.v1.model.authDto.RegisterRequest;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserDetailRepository userDetailRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationService(UserDetailRepository userDetailRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userDetailRepository = userDetailRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        if(userDetailRepository.findByEmail(registerRequest.getEmail()).orElse(null) != null) {
            throw new BadRequestException("Email Already Registered");
        }
        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName(registerRequest.getFirstname());
        userDetail.setLastName(registerRequest.getLastname());
        userDetail.setEmail(registerRequest.getEmail());
        userDetail.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userDetail.setRole(Role.USER);
        userDetailRepository.save(userDetail);
        String jwtToken = jwtService.generateToken(userDetail);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        UserDetail userDetail = userDetailRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(userDetail);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }
}
