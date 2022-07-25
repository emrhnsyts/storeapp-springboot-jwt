package com.emrhnsyts.store.config;

import com.emrhnsyts.store.security.JwtAuthenticationEntryPoint;
import com.emrhnsyts.store.security.JwtTokenFilter;
import com.emrhnsyts.store.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AppUserService appUserService;
    private final JwtTokenFilter jwtTokenFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/users/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/users/register").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/comments/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/likes/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
