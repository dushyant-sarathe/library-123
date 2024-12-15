package com.libraray.config;

import com.libraray.service.LibraryUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public LibraryUserDetailsService detailsService() {
        return new LibraryUserDetailsService();
    }

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Qualifier("delegatedAuthenticationEntryPoint")
    private final DelegatedAuthenticationEntryPoint authEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(detailsService());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                                .requestMatchers("/", "/index.html","/home.html","/signup.html","/book.html","/employee_form.html","/students.html", "/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/auth/signup").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/auth/signin").permitAll()
                                .requestMatchers(HttpMethod.GET, "/home").permitAll()
                                .requestMatchers(HttpMethod.GET, "/signup").permitAll()
                                .requestMatchers(HttpMethod.GET, "/books").permitAll()
                                .requestMatchers(HttpMethod.GET, "/employee").permitAll()
                                .requestMatchers(HttpMethod.GET, "/student").permitAll()


                                .requestMatchers(HttpMethod.POST, "/api/employees/save").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/employees").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/employees/department/{department}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/employees/{id}").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/api/employees/{id}").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/api/employees/{id}").permitAll()

                                .requestMatchers(HttpMethod.POST, "/api/books").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/books/{bookId}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/books/{bookId}").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/api/books/{bookId}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/books/batch").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/api/books/{bookId}").permitAll()


                                .requestMatchers(HttpMethod.POST,"/api/user/create").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/user/update/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/user/search").hasAnyRole("ELEVATE", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/user/find").hasAnyRole("ELEVATE", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/user/**").authenticated()


                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/elevate/**").hasAnyRole("ELEVATE", "ADMIN")
                                .anyRequest().authenticated()
                ).authenticationProvider(authProvider()
                );

        return http.build();
    }

}
