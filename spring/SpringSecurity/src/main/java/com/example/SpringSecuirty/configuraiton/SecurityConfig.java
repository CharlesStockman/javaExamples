package com.example.SpringSecuirty.configuraiton;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


// Configuration -- Change the way the security filter chain works
// Crate a Config Class
// @EnableWebSecurity
//     1. Enables Spring Security in a Web Application
//     2. Imports the WebSecurityConfiguration Class
//     3. What kind of security is being implemented can have Flux and Socket too.
@Configuration
@Slf4j
public class SecurityConfig {

    @Value("${app.security.configuration.name}")
    private String configurationName;

    // Warning -- Empty when returning the HttpSecurity.build() without any configuration ( methods called )
    // will make your website unsecure.  You can try it by return httpSecurity.build() as the only line.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        SecurityFilterChain chain =  SpringSecurityConfigFactory.selectConfiguration(httpSecurity, configurationName).build();
        log.error("Charles Stockman : {}", httpSecurity.toString());

        return chain;
    }
}

@Slf4j
class SpringSecurityConfigFactory {

    /**
     * Where the correct configuration is based the property key (app.security.configuration.name:
     *
     * @param httpSecurity -- A builder to create the SecurityFilterChain
     * @param configurationKey -- The key used to select the correct configuration
     * @throws Exception -- Could not build the SecurityFilterChain from the HttpSecurity Builder
     */
    public static HttpSecurity selectConfiguration(HttpSecurity httpSecurity, String configurationKey) throws Exception {

        log.error("Charles Stockman -- Configuring for  property app.security.configuration.name with value {}", configurationKey);

        HttpSecurity httpSecurityConfigured = switch(configurationKey) {
            case "absoluteAccess"   -> SpringSecurityConfigFactory.absoluteAccess(httpSecurity);
            case "firstTry"         -> SpringSecurityConfigFactory.firstTry(httpSecurity);
            case "secondTry"        -> SpringSecurityConfigFactory.secondTry(httpSecurity);
            case "thirdTry"         -> SpringSecurityConfigFactory.thirdTry(httpSecurity);
            default -> throw new IllegalArgumentException("Invalid value for \"app.security.configuration.name\" -- " + configurationKey);
        };

        return httpSecurityConfigured;
    }

    /**
     * Experiment with an HttpSecurity where no methods are called on it.  There is no security and will be able to
     * enter the app with authentication.
     *
     * @param httpSecurity -- A builder to create the SecurityFilterChain
     * @return HttpSecurity -- A fully configured HttpSecurity ready to be built into a SecurityFilterChain
     * @throws Exception One of the HttpSecurity Functions have failed
     */
    public static HttpSecurity absoluteAccess(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity;
    }

    /**
     * Any user must login through the default Login Page or postman
     *
     * @param httpSecurity -- A builder to create the SecurityFilterChain
     * @return HttpSecurity -- A fully configured HttpSecurity ready to be built into a SecurityFilterChain
     * @throws Exception One of the HttpSecurity Functions have failed
     */
    @Bean
    private static HttpSecurity firstTry(HttpSecurity httpSecurity) throws Exception {

        // Line 1 -- Disable here to show other ways how the Put, Delete and Post can be done in further examples.
        // Line 2 -- No one should access page without authentication
        // Line 3 -- Implement a form login with the default properties
        // Line 4 -- Implement for REST Access
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic((Customizer.withDefaults()));

        return httpSecurity;
    }

    /**
     * Create a stateless section where authenticate everytime a request is done.
     *
     * Does not work for Web Login Pages (Default Login Form) since it keeps returning to the login form.
     * Need to remove the httpSecurity.formLogin() and the browser will authenticate and the correct web page
     * will be shown.
     *
     * @param httpSecurity -- A builder to create the SecurityFilterChain
     * @return HttpSecurity -- A fully configured HttpSecurity ready to be built into a SecurityFilterChain
     * @throws Exception One of the HttpSecurity Functions have failed
     */
    @Bean
    private static HttpSecurity secondTry(HttpSecurity httpSecurity) throws Exception {

        // Line 1 -- Disable here to show other ways how the Put, Delete and Post can be done in further examples.
        // Line 2 -- No one should access page without authentication
        // Line 3 -- Implement a form login with the default properties
        // Line 4 -- Implement for REST Access
        // line 5 -- Made the session stateless
        // Login through a form
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .httpBasic((Customizer.withDefaults()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return httpSecurity;
    }
}
