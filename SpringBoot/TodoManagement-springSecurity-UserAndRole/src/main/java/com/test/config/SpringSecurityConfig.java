package com.test.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((authorize) -> {
            //authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
            //authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
            //authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
            //authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasAnyRole("ADMIN","USER");
            //authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN","USER");

            //To Expose the Get End points Publicly
            //authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
            authorize.anyRequest().authenticated();
        });
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
    //commented the below as we are using DB Authentication
    /*
        @Bean
        public UserDetailsService userDetailsService() {

            UserDetails userUser = User.builder()
                    .username("punnu")
                    .password(passwordEncoder().encode("punnu0706"))
                    .roles("USER")
                    .build();

            UserDetails adminUser = User.builder()
                    .username("pradeep")
                    .password(passwordEncoder().encode("pradeep491"))
                    .roles("ADMIN")
                    .build();

            return new InMemoryUserDetailsManager(userUser, adminUser);
        }
    */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
