package umc.stockoneqback.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import umc.stockoneqback.auth.utils.TokenProvider;
import umc.stockoneqback.global.annotation.AuthArgumentResolver;
import umc.stockoneqback.global.annotation.ExtractPayloadArgumentResolver;
import umc.stockoneqback.global.annotation.ExtractTokenArgumentResolver;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final TokenProvider tokenProvider;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ExtractTokenArgumentResolver(tokenProvider));
        resolvers.add(new ExtractPayloadArgumentResolver(tokenProvider));
        resolvers.add(new AuthArgumentResolver());
    }
}
