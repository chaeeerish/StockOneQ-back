package umc.stockoneqback.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import umc.stockoneqback.global.security.filters.JwtRequestFilter;
import umc.stockoneqback.global.security.handler.JwtAccessDeniedHandler;
import umc.stockoneqback.global.security.handler.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**");
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->
                web
                        .ignoring()
                        .requestMatchers(
                                PathRequest.toStaticResources().atCommonLocations()
                        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/profile"),
                                        AntPathRequestMatcher.antMatcher("/api/auth/login"),
                                        AntPathRequestMatcher.antMatcher("/api/user/sign-up/manager"),
                                        AntPathRequestMatcher.antMatcher("/api/user/sign-up/part-timer"),
                                        AntPathRequestMatcher.antMatcher("/api/user/sign-up/supervisor"),
                                        AntPathRequestMatcher.antMatcher("/api/user/update/password"),
                                        AntPathRequestMatcher.antMatcher("/api/user/find-id")
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .headers(
                        headersConfigurer ->
                                headersConfigurer
                                        .frameOptions(
                                                HeadersConfigurer.FrameOptionsConfig::sameOrigin
                                        )
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

//        http.cors();
//        http.csrf().disable();
//
//        http.formLogin().disable();
//        http.httpBasic().disable();
//
//        http.exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .accessDeniedHandler(jwtAccessDeniedHandler);
//
//        http.headers().frameOptions().sameOrigin();
//
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.authorizeHttpRequests()
//                    .requestMatchers("/profile").permitAll()
//                    .requestMatchers("/api/auth/login").permitAll()
//                    .requestMatchers("/api/user/sign-up/manager").permitAll()
//                    .requestMatchers("/api/user/sign-up/part-timer").permitAll()
//                    .requestMatchers("/api/user/sign-up/supervisor").permitAll()
//                    .requestMatchers("/api/user/update/password").permitAll()
//                    .requestMatchers("/api/user/find-id").permitAll()
//                .anyRequest().authenticated();
//
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
    }
}
