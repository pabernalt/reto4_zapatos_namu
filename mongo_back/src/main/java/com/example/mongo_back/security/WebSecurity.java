package com.example.mongo_back.security;

import com.example.mongo_back.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UsuarioService usuarioService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment env;
    @Autowired
    public WebSecurity(Environment env, UsuarioService usuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.env = env;
        this.usuarioService = usuarioService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.formLogin().disable();
        http.authorizeRequests().antMatchers("/api/login", "/api/register").permitAll()
                        .anyRequest().authenticated()
                        .and()
                .addFilter(getAuthenticationFilter());
        http.headers().frameOptions().disable();


    }
    private AuthenticationFilter getAuthenticationFilter() throws Exception{
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(), usuarioService, env );
        authenticationFilter.setFilterProcessesUrl("/api/login");
        return authenticationFilter;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(bCryptPasswordEncoder);
    }
}
