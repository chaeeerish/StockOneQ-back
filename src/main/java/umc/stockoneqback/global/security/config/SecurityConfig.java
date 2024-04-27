package umc.stockoneqback.global.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import umc.stockoneqback.auth.service.jwt.JwtTokenIssuer;
import umc.stockoneqback.auth.utils.TokenProvider;
import umc.stockoneqback.auth.utils.TokenResponseWriter;
import umc.stockoneqback.global.security.filter.JsonAuthenticationFilter;
import umc.stockoneqback.global.security.filter.JwtAuthorizationExceptionTranslationFilter;
import umc.stockoneqback.global.security.filter.JwtAuthorizationFilter;
import umc.stockoneqback.global.security.filter.LogoutExceptionTranslationFilter;
import umc.stockoneqback.global.security.handler.*;
import umc.stockoneqback.global.security.properties.CorsProperties;
import umc.stockoneqback.global.security.service.JsonAuthenticationProvider;
import umc.stockoneqback.global.security.service.RdbUserDetailsService;
import umc.stockoneqback.user.domain.UserRepository;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final CorsProperties corsProperties;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final JwtTokenIssuer jwtTokenIssuer;
    private final TokenResponseWriter tokenResponseWriter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOriginPatterns(corsProperties.getAllowedOriginPatterns());
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PATCH", "PUT", "DELETE", "HEAD", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", corsConfiguration);
        return source;
    }

    @Bean
    public UserDetailsService rdbUserDetailsService() {
        return new RdbUserDetailsService(userRepository);
    }

    @Bean
    public AuthenticationProvider jsonAuthenticationProvider() {
        return new JsonAuthenticationProvider(rdbUserDetailsService(), passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        final ProviderManager authenticationManager = (ProviderManager) authenticationConfiguration.getAuthenticationManager();
        authenticationManager.getProviders().add(jsonAuthenticationProvider());
        return authenticationManager;
    }

    @Bean
    public AuthenticationSuccessHandler jsonAuthenticationSuccessHandler() {
        return new JsonAuthenticationSuccessHandler(jwtTokenIssuer, tokenResponseWriter, objectMapper);
    }

    @Bean
    public AuthenticationFailureHandler jsonAuthenticationFailureHandler() {
        return new JsonAuthenticationFailureHandler(objectMapper);
    }

    @Bean
    public JsonAuthenticationFilter jsonAuthenticationFilter() throws Exception {
        final JsonAuthenticationFilter authenticationFilter = new JsonAuthenticationFilter(objectMapper);
        authenticationFilter.setAuthenticationManager(authenticationManager());
        authenticationFilter.setAuthenticationSuccessHandler(jsonAuthenticationSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(jsonAuthenticationFailureHandler());
        return authenticationFilter;
    }

    @Bean
    public AuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint(objectMapper);
    }

    @Bean
    public AccessDeniedHandler jwtAccessDeniedHandler() {
        return new JwtAccessDeniedHandler(objectMapper);
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(tokenProvider, userRepository);
    }

    @Bean
    public JwtAuthorizationExceptionTranslationFilter jwtAuthorizationExceptionTranslationFilter() {
        return new JwtAuthorizationExceptionTranslationFilter(jwtAccessDeniedHandler());
    }

    @Bean
    public LogoutHandler jwtLogoutTokenCheckHandler() {
        return new JwtLogoutTokenCheckHandler();
    }

    @Bean
    public LogoutSuccessHandler jwtLogoutSuccessHandler() {
        return new JwtLogoutSuccessHandler(jwtTokenIssuer);
    }

    @Bean
    public LogoutExceptionTranslationFilter logoutExceptionTranslationFilter() {
        return new LogoutExceptionTranslationFilter(jwtAccessDeniedHandler());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(request ->
                request
                        .requestMatchers("/profile").permitAll()
                        .requestMatchers("/v3/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/api/token/reissue").permitAll()
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/auth/logout").permitAll()
                        .requestMatchers("/api/user/sign-up/**").permitAll()
                        .requestMatchers("/api/user/find-id").permitAll()
                        .anyRequest().authenticated()
        );

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.addFilterBefore(logoutExceptionTranslationFilter(), LogoutFilter.class);
        http.addFilterBefore(jwtAuthorizationFilter(), LogoutExceptionTranslationFilter.class);
        http.addFilterBefore(jwtAuthorizationExceptionTranslationFilter(), JwtAuthorizationFilter.class);
        http.addFilterAt(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.logout(logout ->
                logout.logoutUrl("/api/auth/logout")
                        .clearAuthentication(true)
                        .deleteCookies(TokenResponseWriter.REFRESH_TOKEN_COOKIE)
                        .addLogoutHandler(jwtLogoutTokenCheckHandler())
                        .logoutSuccessHandler(jwtLogoutSuccessHandler())
        );

        http.exceptionHandling(exception ->
                exception.authenticationEntryPoint(jwtAuthenticationEntryPoint())
                        .accessDeniedHandler(jwtAccessDeniedHandler())
        );

        return http.build();
    }
}
