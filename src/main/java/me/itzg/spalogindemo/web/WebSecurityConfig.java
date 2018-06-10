package me.itzg.spalogindemo.web;

import me.itzg.spring.security.spa.SimpleLogoutSuccessHandler;
import me.itzg.spring.security.spa.SinglePageAppConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final WebProperties webProperties;

    @Autowired
    public WebSecurityConfig(WebProperties webProperties) {
        this.webProperties = webProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/profile").permitAll()
                .antMatchers("/api/**").fullyAuthenticated()
                .antMatchers("/**").permitAll()
                .anyRequest().fullyAuthenticated()

                .and().logout().logoutSuccessHandler(new SimpleLogoutSuccessHandler())
                .and().csrf().disable() // CSRF is less helpful (and a little annoying) with single page apps
                .oauth2Login().loginPage("/")

                .and().apply(new SinglePageAppConfigurer<>()).registerUrl("/register/local").loginUrl("/login/local")
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsManager())
                .passwordEncoder(passwordEncoder())
                .and()
                .inMemoryAuthentication();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager();
    }
}
