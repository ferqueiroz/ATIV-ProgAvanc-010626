package com.bn.demo.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Define que a classe será uma configuração de Bean para o spring
@EnableWebSecurity // Ativa o modulo de segurança do spring security na aplicação
public class SecurityFilter {

    @Bean // Registra o retorno do metodo como um bean gerenciado pelo container Spring.
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.GET, "/produtos").permitAll()
                            .requestMatchers(HttpMethod.POST, "/produtos").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/produtos/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/produtos/**").permitAll()
                            .anyRequest().authenticated()
                )
                .build();
    }

}
