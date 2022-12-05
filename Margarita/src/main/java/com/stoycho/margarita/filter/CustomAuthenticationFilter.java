package com.stoycho.margarita.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stoycho.margarita.bruteForceProtection.LoginAttemptService;
import com.stoycho.margarita.exception.BlockedIpException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private final LoginAttemptService loginAttemptService;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager,LoginAttemptService loginAttemptService){
        this.authenticationManager=authenticationManager;
        this.loginAttemptService= loginAttemptService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, BlockedIpException {

        String ip = getClientIP(request);
        if (loginAttemptService.isBlocked(ip)) {
            throw new BlockedIpException("blocked");
        }


        String username=request.getParameter("username");
        String password=request.getParameter("password");
        log.info("Username is: {}",username); log.info("Password is: {}",password);
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        return authenticationManager.authenticate(authenticationToken);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User)authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(getSecret().getBytes());
        String access_token= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 2 * 60 * 1000))//Default: 2 * 60 * 1000
                .withIssuer(request.getRequestURI())
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))//Default:5 * 60 * 1000
                .withIssuer(request.getRequestURI())
                .sign(algorithm);
        Map<String,String> tokens=new HashMap<>();
        tokens.put("username",user.getUsername());
        tokens.put("roles", StringUtils.join(Collections.singleton(user.getAuthorities().toString()), ','));
        tokens.put("access_token",access_token);
        tokens.put("refresh_token",refresh_token);


        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);
    }
    private String getSecret() throws IOException{
        String file ="extra/secret.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();
        reader.close();
        return currentLine;
    }


    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}
