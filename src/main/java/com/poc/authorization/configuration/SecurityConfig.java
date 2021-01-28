package com.poc.authorization.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Disable security on our custom endpoint(s) (we have a custom authentication management)
                .antMatchers("/auth/**").permitAll()

                // Disable security on /actuator/health
                .antMatchers("/actuator/health").permitAll()

                // Disable security for swagger
                .antMatchers("/v3/api-docs/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**").permitAll()


                // Disable default /login - /logout
                .antMatchers(HttpMethod.GET, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/logout").permitAll()
                .anyRequest().authenticated()
                .and()

                // Prevent headers caching
                .headers().cacheControl().disable()
                .and()

                // CSRF management
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers(
                        new AntPathRequestMatcher("/actuator/**"),
                        new AntPathRequestMatcher("/auth/**")
                )

                // At the end enable httpBasic for ALL others admin's endpoints
                .and()
                .httpBasic().disable();
    }
}
