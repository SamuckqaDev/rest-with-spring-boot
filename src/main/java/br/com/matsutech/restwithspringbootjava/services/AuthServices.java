package br.com.matsutech.restwithspringbootjava.services;


import br.com.matsutech.restwithspringbootjava.data.vo.v1.AccountCredentialsVO;
import br.com.matsutech.restwithspringbootjava.data.vo.v1.TokenVO;
import br.com.matsutech.restwithspringbootjava.repositories.UserRepository;
import br.com.matsutech.restwithspringbootjava.secutiryJwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServices {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;

    @Autowired(required = false)
    public AuthServices(JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, UserRepository repository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
    }

    public ResponseEntity<Object> signIn(AccountCredentialsVO data) throws Exception {

        var username = data.getUsername();
        var password = data.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        var userRepository = repository.findByUsername(username);

        try {
            if (userRepository != null) {
                var tokenResponse = jwtTokenProvider.createAccessToken(username, (List<String>) userRepository.getRoles());
                return ResponseEntity.ok(tokenResponse);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception ex) {
            throw new BadCredentialsException("Invalid username or password supplied!");
        }
    }
}
