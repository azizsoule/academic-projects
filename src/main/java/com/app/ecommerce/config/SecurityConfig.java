package com.app.ecommerce.config;

import com.app.ecommerce.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        // @formatter:on
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers(
                            "/login*",
                            "/logout*",
                            "/resources/**",
                            "/css/**",
                            "/assets/**",
                            "/vendor/**",
                            "/js/**",
                            "/ajax/**",
                            "/*.js",
                            "/api/**",
                            "/e-media/**",
                            "/www.google-analytics.com/analytics.js"
//                            "/scss/**"
                    ).permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("username|email")
                    .permitAll()
                    .and()
                .logout()
                    .invalidateHttpSession(false)
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                    .and()
                .rememberMe()
                    .userDetailsService(userDetailsService)
                    .key(Constants.REMEMBER_ME_KEY)
                    .tokenValiditySeconds(86400);

        // @formatter:on
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(Constants.ENCRYPTION_STRENGTH);
    }
}
