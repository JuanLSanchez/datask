package es.juanlsanchez.datask.security.jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import es.juanlsanchez.datask.config.AppConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class TokenProvider {

    @Inject
    private AppConfig appConfig;

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private String secretKey;

    private long tokenValidityInSeconds;

    private long tokenValidityInSecondsForRememberMe;

    @PostConstruct
    public void init() {
	this.secretKey = appConfig.getSecurity()
		.getAuthentication()
		.getJwt()
		.getSecret();

	this.tokenValidityInSeconds = 1000L
		* appConfig.getSecurity()
			.getAuthentication()
			.getJwt()
			.getTokenValidityInSeconds();
	this.tokenValidityInSecondsForRememberMe = 1000L
		* appConfig.getSecurity()
			.getAuthentication()
			.getJwt()
			.getTokenValidityInSecondsForRememberMe();
    }

    /**
     * Function to create a JWT token with the user and the roles
     * 
     * @param Authentication
     *            Authentication object from Spring Security. This object
     *            provide the user and the roles
     * @param RememberMe
     *            true for long duration
     * @return A JWT token
     */
    public String createToken(Authentication authentication, Boolean rememberMe) {
	String authorities = authentication.getAuthorities()
		.stream()
		.map(authority -> authority.getAuthority())
		.collect(Collectors.joining(","));

	long now = System.currentTimeMillis();
	Date validity;
	if (rememberMe) {
	    validity = new Date(now
		    + this.tokenValidityInSecondsForRememberMe);
	} else {
	    validity = new Date(now
		    + this.tokenValidityInSeconds);
	}

	String result = Jwts.builder()
		.setSubject(authentication.getName())
		.claim(AUTHORITIES_KEY, authorities)
		.signWith(SignatureAlgorithm.HS512, secretKey)
		.setExpiration(validity)
		.compact();
	return result;
    }

    /**
     * Function to obtain the Authentication object of Spring Security whit the
     * JWT token.
     * 
     * @param token
     *            A JWT token.
     * @return A authentication object from Spring Security.
     */
    public Authentication getAuthentication(String token) {
	Claims claims = Jwts.parser()
		.setSigningKey(secretKey)
		.parseClaimsJws(token)
		.getBody();

	Collection<? extends GrantedAuthority> authorities = Arrays.asList(claims.get(AUTHORITIES_KEY)
		.toString()
		.split(","))
		.stream()
		.map(authority -> new SimpleGrantedAuthority(authority))
		.collect(Collectors.toList());

	User principal = new User(claims.getSubject(), "", authorities);

	return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * Function to validate the token
     * 
     * @param authToken
     *            String with the token
     * @return True if the string is a correct token
     */
    public boolean validateToken(String authToken) {
	try {
	    Jwts.parser()
		    .setSigningKey(secretKey)
		    .parseClaimsJws(authToken);
	    return true;
	} catch (SignatureException e) {
	    log.info("Invalid JWT signature: "
		    + e.getMessage());
	    return false;
	}
    }
}
