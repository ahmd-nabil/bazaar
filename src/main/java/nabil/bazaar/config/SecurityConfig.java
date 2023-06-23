package nabil.bazaar.config;

import nabil.bazaar.controllers.CategoryController;
import nabil.bazaar.controllers.CountryController;
import nabil.bazaar.controllers.ProductController;
import nabil.bazaar.controllers.StateController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Ahmed Nabil
 */
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // if Spring MVC is on classpath and no CorsConfigurationSource is provided,
                // Spring Security will use CORS configuration provided to Spring MVC
                // found here : https://docs.spring.io/spring-security/site/docs/5.3.4.RELEASE/reference/html5/#cors
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)          // disabled for now, TODO
                .authorizeHttpRequests(
                        requests ->
                                requests
                                        .requestMatchers("/error").permitAll()
                                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                                        .requestMatchers(HttpMethod.GET,
                                            ProductController.PRODUCT_API+"/**",
                                                CategoryController.CATEGORY_API + "/**",
                                                CountryController.COUNTRY_API + "/**",
                                                StateController.STATE_API + "/**").permitAll()
                                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer.jwt(Customizer.withDefaults()))
                .build();
    }
}
