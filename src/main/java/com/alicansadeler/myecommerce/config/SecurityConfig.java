package com.alicansadeler.myecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf(csrf -> csrf.disable()) // csrf korumasını devre dışı bırak
                .authorizeHttpRequests(auth -> { // yetkilendirme ayarları
                    auth.requestMatchers("/auth/**").permitAll();// tüm kullanıcılara açık - user kayıt


                    // kullanıcı ile ilgili işlemler admin tarafından yapılmalı ?
                    auth.requestMatchers(HttpMethod.GET,"/account/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.POST,"/account/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT,"/account/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE,"/account/**").hasAuthority("ADMIN");

                    // adres işlemleri için silme ekleme güncelleme user ve admin, getirme DB işlemleri admine özel
                    auth.requestMatchers(HttpMethod.GET,"/profile/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.POST,"/profile/**").hasAnyAuthority("ADMIN", "USER");
                    auth.requestMatchers(HttpMethod.PUT,"/profile/**").hasAnyAuthority("ADMIN", "USER");
                    auth.requestMatchers(HttpMethod.DELETE,"/profile/**").hasAnyAuthority("ADMIN", "USER");

                    auth.requestMatchers(HttpMethod.GET, "/product/**") // admin ve usera açık
                            .hasAnyAuthority("ADMIN", "USER");
                    auth.requestMatchers(HttpMethod.POST, "/product/**").hasAuthority("ADMIN");
                    auth.anyRequest().authenticated(); // Diğer Tüm İstekler: Kimlik doğrulama gerektirir
                })

                .formLogin(Customizer.withDefaults()) // Form tabanlı kimlik doğrulama (username ve şifre ile giriş)
                // varsayılan ayarlarla etkinleştirilir.

                .httpBasic(Customizer.withDefaults()) //HTTP Basic Authentication varsayılan ayarlarla etkinleştirilir.

                .build(); //Yapılandırmayı tamamlar ve SecurityFilterChain nesnesi olarak döner.
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("/**"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}



