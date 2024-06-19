package com.pplanaturmo.inrappproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.pplanaturmo.inrappproject.auth.token.JwtAuthFilter;

import lombok.RequiredArgsConstructor;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

        private static final String[] WHITE_LIST_URL = { "/api/v1/auth/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html",
                        "/api/range-inr/all",
                        "/api/dose-pattern/all"};
        private final JwtAuthFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;
        private final LogoutHandler logoutHandler;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;


        // FUNCIONA

//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//                http
//                                .cors(cors -> cors.disable())
//                                .csrf(csrf -> csrf.disable())
//                                .authorizeHttpRequests(authorize -> authorize
//                                                .anyRequest().permitAll())
//                                .sessionManagement(management -> management
//                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//                return http.build();
//        }

//         @Bean
//         public SecurityFilterChain filterChain(HttpSecurity http,
//         AuthenticationProvider authenticationProvider)
//         throws Exception {
//         return http
//         .authorizeHttpRequests((authz) -> authz
//         .requestMatchers("/api/v1/auth/**",
//         "/v2/api-docs",
//         "/v3/api-docs",
//         "/v3/api-docs/**",
//         "/swagger-resources",
//         "/swagger-resources/**",
//         "/configuration/ui",
//         "/configuration/security",
//         "/swagger-ui/**",
//         "/webjars/**",
//         "/swagger-ui.html",
//         "/api/user/create")
//         .permitAll()
//         .requestMatchers("/**").authenticated())
//         .httpBasic(withDefaults()).csrf(AbstractHttpConfigurer::disable)
//         .sessionManagement((session) -> session
//         .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//         .authenticationProvider(authenticationProvider)
//         .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//         .build();
//         }

        // @Bean
        // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http
        // .cors(AbstractHttpConfigurer::disable)
        // .csrf(AbstractHttpConfigurer::disable)
        // .authorizeHttpRequests(auth -> {
        // //
        // auth.requestMatchers(WHITE_LIST_URL).permitAll().anyRequest().authenticated();
        // auth.requestMatchers("/api/v1/auth/**").permitAll().anyRequest()
        // .authenticated();
        // })
        // .sessionManagement(session -> session
        // .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        // return http.build();
        // }

        // @Bean
        // public SecurityFilterChain filterChain(HttpSecurity http,
        // AuthenticationProvider authenticationProvider)
        // throws Exception {
        // return http
        // .authorizeHttpRequests((authz) -> authz
        // .requestMatchers("/api/v1/auth/**",
        // "/v2/api-docs",
        // "/v3/api-docs",
        // "/v3/api-docs/**",
        // "/swagger-resources",
        // "/swagger-resources/**",
        // "/configuration/ui",
        // "/configuration/security",
        // "/swagger-ui/**",
        // "/webjars/**",
        // "/swagger-ui.html",
        // "/api/user/create")
        // .permitAll())
        // .authorizeHttpRequests((authz) -> authz
        // .requestMatchers("/**")
        // .authenticated())
        // .httpBasic(withDefaults())
        // .csrf((csrf) -> csrf.disable())
        // .sessionManagement((session) -> session
        // .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // .authenticationProvider(authenticationProvider)
        // .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        // .build();
        // }


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(authz -> authz
                                .requestMatchers(WHITE_LIST_URL).permitAll()
                                .anyRequest().authenticated()
                        )
                        .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                        .authenticationProvider(authenticationProvider)
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }


}
