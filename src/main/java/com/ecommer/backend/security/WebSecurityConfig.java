package com.ecommer.backend.security;

import com.ecommer.backend.service.CurrentUserService;
import com.ecommer.backend.session.SessionFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final CurrentUserService service;
    private final SessionFilter sessionFilter;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        final String[] allowed_resources = {
                "/api/v1/login",
                "/h2-console/**",
                "/api/v1/customer/register",
                "/api/v1/customer/get/user",
                "/api/v1/produto/new",
                "/api/v1/produto/all",
                "/api/v1/customer/update/profile/**",
                "/api/v1/order/**"
        };

        //Resolving CORS
        http.cors().and().csrf().disable();

        //Exception Handling
        http.exceptionHandling().authenticationEntryPoint((request, response, authException)
                -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage())).and();


        //Allow H2 Database Connection
        //http.authorizeRequests().antMatchers("/h2-console/**");
        //http.headers().frameOptions().disable();

        //Normal Filter Chain
        http
                .authorizeRequests()
                .antMatchers(allowed_resources).permitAll()
                .anyRequest().authenticated()
                .and().headers().frameOptions().disable();;

        http.addFilterBefore(sessionFilter, UsernamePasswordAuthenticationFilter.class);

        return http.httpBasic().and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        final List<GlobalAuthenticationConfigurerAdapter> configurers = new ArrayList<>();
        configurers.add(new GlobalAuthenticationConfigurerAdapter() {
                            @Override
                            public void configure(AuthenticationManagerBuilder auth) throws Exception {
                                auth.userDetailsService(service).passwordEncoder(passwordEncoder());
                            }
                        }
        );
        return authConfig.getAuthenticationManager();
    }


}
