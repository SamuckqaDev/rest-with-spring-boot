package br.com.matsutech.restwithspringbootjava.secutiryJwt;

import br.com.matsutech.restwithspringbootjava.data.vo.v1.TokenVO;
import br.com.matsutech.restwithspringbootjava.exceptions.InvalidJwtAuthenticationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; //1H

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenVO createAccessToken(String username, List<String> roles) {
        var now = new Date();
        var valid = new Date(now.getTime() + validityInMilliseconds);
        var accessToken = getAccessToken(username, roles, now, valid);
        var refreshToken = getRefreshToken(username, roles, now);
        return new TokenVO(username, true, now, valid, accessToken, refreshToken);
    }


    private String getAccessToken(String username, List<String> roles, Date now, Date valid) {
        var issueUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath().build()
                .toString();
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(valid)
                .withSubject(username)
                .withIssuer(issueUrl)
                .sign(algorithm)
                .strip();
    }

    private String getRefreshToken(String username, List<String> roles, Date now) {

        var validRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3));
        var issueUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath().build()
                .toString();

        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validRefreshToken)
                .withSubject(username)
                .sign(algorithm)
                .strip();
    }

    public Authentication getAuthentication(String token) {
        var decodedJwt = decodeToken(token);

        var userDetails = this.userDetailsService.loadUserByUsername(decodedJwt.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodeToken(String token) {
        var algorithm = Algorithm.HMAC256(secretKey.getBytes());
        var verifier = JWT.require(algorithm).build();
        var decodedJwt = verifier.verify(token);

        return decodedJwt;
    }

    public String resolveToken(HttpServletRequest request) {
        var bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        var decodedJWT = decodeToken(token);
        try {
            if (decodedJWT.getExpiresAt().before(new Date())) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            throw new InvalidJwtAuthenticationException("Expired Token or invalid token!");
        }
    }
}
