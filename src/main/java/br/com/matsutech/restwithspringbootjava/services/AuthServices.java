package br.com.matsutech.restwithspringbootjava.services;


import br.com.matsutech.restwithspringbootjava.data.vo.v1.AccountCredentialsVO;
import br.com.matsutech.restwithspringbootjava.data.vo.v1.TokenVO;
import br.com.matsutech.restwithspringbootjava.repositories.UserRepository;
import br.com.matsutech.restwithspringbootjava.secutiryJwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    public ResponseEntity signin(AccountCredentialsVO data) throws Exception{

            var username = data.getUsername();
            var password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);

            var tokeResponse = new TokenVO();

           return  ResponseEntity.ok("ok");
    }


}
