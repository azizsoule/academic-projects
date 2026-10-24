package com.app.ecommerce.configs;

import com.app.ecommerce.utils.Constants;
import com.app.ecommerce.utils.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers(
            "/"+Route.CART+"*",
                        "/"+Route.CHECKOUT+"*",
                        "/"+Route.MY_ACCOUNT+"*",
                        "/"+Route.WISHLIST+"*",
                        "/"+Route.ORDER_HISTORY+"*",
                        "/"+Route.ORDER_INFORMATION+"/**",
                        "/"+Route.GIFT_VOUCHER+"/**"
                    ).authenticated()
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginPage("/"+Route.LOGIN)
                    .usernameParameter("email")
                    .permitAll()
                    .and()
                .logout()
                    .invalidateHttpSession(false)
                    .logoutUrl("/"+Route.LOGOUT)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                    .and()
                .rememberMe()
                    .key("C@$H-@PP")
                    .tokenValiditySeconds(86400)
                    .and()
                .headers()
                    .frameOptions()
                    .disable();
    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(Constants.ENCODER_LENGTH);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

}
