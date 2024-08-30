package com.example.SpringSecuirty.configuraiton;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


// Configuration -- Change the way the security filter chain works
// Crate a Config Class

//
// UsersDetailsService -- Allows customizing of the properties.
//    It is an interface
@Configuration
@Slf4j
public class SecurityConfig {

    @Value("${app.security.configuration.name}")
    private String configurationName;

    // Warning -- Empty when returning the HttpSecurity.build() without any configuration ( methods called )
    // will make your website unsecure.  You can try it by return httpSecurity.build() as the only line.
    @Bean
    //@Profile("SecurityFilterChain")
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        SecurityFilterChain chain =  SpringSecurityConfigFactory.selectConfiguration(httpSecurity, configurationName).build();
        log.error("Charles Stockman : configuration name = {}", configurationName);
        log.error("Charles Stockman : {}", httpSecurity.toString());
        log.error("Charles Stockman : Security Filter Chain is {}", chain.toString());
        return chain;
    }

    /**
     * Developer Configuration using a known Class
     *
     * InMemoryUserDetailsManager -- Will contain information about the users (name, password, roles) and when used
     * it will not use the username/password from the application.properties
     *
     * @return Non-persistent implementation of UserDetailsManager which is backed by an in-memory map.
     *
     * .withDefaultPasswordEncode -- Encodes password so no one can read it.
     */
    @Bean
    @Profile("DataInMemory")
    private static UserDetailsService userDetailsServiceWithKnownClass()  {
        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("chuck.stockman")
                .password("password")
                .roles("USER")
                .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("harsh")
                .password("password")
                .roles("ADMIN")
                .build();

        // Implements UserDetailsService
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(user1, user2);

        return manager;
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
