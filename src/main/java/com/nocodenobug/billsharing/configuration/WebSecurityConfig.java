package com.nocodenobug.billsharing.configuration;

import com.nocodenobug.billsharing.security.AuthEntryPointJwt;
import com.nocodenobug.billsharing.security.AuthTokenFilter;
import com.nocodenobug.billsharing.security.RestAuthenticationEntryPoint;
import com.nocodenobug.billsharing.security.UserDetailsServiceImpl;
import com.nocodenobug.billsharing.security.oauth2.CustomOAuth2UserService;
import com.nocodenobug.billsharing.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.nocodenobug.billsharing.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.nocodenobug.billsharing.security.oauth2.OAuth2AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
         securedEnabled = true,
         jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // other public endpoints of your API may be appended to this array
            "/ws/**",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests()
//                .antMatchers("/api/v1/auth/**").permitAll()
////                .antMatchers("/api/**").permitAll()
//                .antMatchers(AUTH_WHITELIST).permitAll()
//                .antMatchers("/**").authenticated();
//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.cors().and().csrf().disable()
                .sessionManagement()
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                  .and()
                .exceptionHandling()
                  .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                  .and()
                .authorizeRequests()
                  .antMatchers("/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js").permitAll()
                  .antMatchers("/api/v1/auth/**").permitAll()
                  .antMatchers(AUTH_WHITELIST).permitAll()
                  .antMatchers("/**").authenticated()
                  .and()
                .oauth2Login()
                   .authorizationEndpoint()
                      .baseUri("/oauth2/authorize")
                      .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                      .and()
                  .redirectionEndpoint()
                      .baseUri("/oauth/callback/*")
                      .and()
                .userInfoEndpoint()
                      .userService(customOAuth2UserService)
                      .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);


//                .antMatchers("/api/v1/auth/**").permitAll()
////                .antMatchers("/api/**").permitAll()
//                .antMatchers(AUTH_WHITELIST).permitAll()
//                .antMatchers("/ws/**").permitAll()
//                .antMatchers("/api/v1/**").authenticated();


        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

}