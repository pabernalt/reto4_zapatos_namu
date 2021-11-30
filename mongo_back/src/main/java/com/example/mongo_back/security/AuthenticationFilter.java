package com.example.mongo_back.security;

import com.example.mongo_back.dto.UsuarioDto;
import com.example.mongo_back.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.simple.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Component
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private UsuarioService usuarioService;
    private Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UsuarioService usuarioService, Environment env) {
        super.setAuthenticationManager(authenticationManager);
        this.usuarioService = usuarioService;
        this.env = env;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            UsuarioDto usuarioDto = new ObjectMapper().readValue(request.getInputStream(), UsuarioDto.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usuarioDto.getUsername(), usuarioDto.getPassword(), new ArrayList<>());
            return getAuthenticationManager().authenticate(token);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        logger.info(env.getProperty("token.secret"));
        String username = ((User)authResult.getPrincipal()).getUsername();
        UserDetails userDetails = usuarioService.loadUserByUsername(username);
        String jwt = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expire"))))
                .signWith(SignatureAlgorithm.HS256, env.getProperty("token.secret"))
                .compact();

        response.addHeader("Authorization","Bearer " + jwt);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        JSONObject obj = new JSONObject();
        obj.put("token", jwt);
        String json = new Gson().toJson(obj);

        response.getWriter().write(json);
        response.getWriter().flush();

    }
}