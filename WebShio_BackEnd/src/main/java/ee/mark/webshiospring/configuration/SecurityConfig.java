package ee.mark.webshiospring.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.signingkey}")
    private String jwtSecretKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthorizationFilter jwtFilter = new JwtAuthorizationFilter(authenticationManager());
        jwtFilter.setSecretKey(jwtSecretKey);

        http
                .cors().and().headers().xssProtection().disable().and()
                .csrf().disable()
                .addFilter(jwtFilter)
                .authorizeRequests()
                .antMatchers("/login", "/signup").permitAll()
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/**").permitAll()
                .antMatchers(HttpMethod.GET , "/items/**").permitAll()
                .antMatchers(HttpMethod.GET , "/items").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
