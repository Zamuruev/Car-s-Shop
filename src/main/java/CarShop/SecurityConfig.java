package CarShop;

import CarShop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.Collections;

@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private CustomLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    public SecurityConfig(UserRepository userRepository,
                          CustomAuthenticationSuccessHandler authenticationSuccessHandler,
                          CustomLogoutSuccessHandler logoutSuccessHandler) {
        this.userRepository = userRepository;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityContextRepository securityContextRepository) throws Exception {
        http
                .authorizeHttpRequests(
                        authorizeHttpRequests ->
                                authorizeHttpRequests
                                        .requestMatchers("/img/**", "/css/**", "/js/**")
                                        .permitAll()
                                        .requestMatchers("/login", "/register", "/", "/model","/model/details/{id}", "/brand", "/brand/details/{id}")
                                        .permitAll()
                                        .anyRequest().authenticated()
                )
                .formLogin(
                        (formLogin) ->
                                formLogin.
                                        loginPage("/login").
                                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                                        /*defaultSuccessUrl("/").*/
                                successHandler(authenticationSuccessHandler).
                                        failureForwardUrl("/login")
                )
                .logout((logout) ->
                        logout.logoutUrl("/logout").
                        logoutSuccessHandler(logoutSuccessHandler).
                                /*logoutSuccessUrl("/login").*/
                                invalidateHttpSession(true)
                ).securityContext(
                        securityContext -> securityContext.
                                securityContextRepository(securityContextRepository)
                );

        return http.build();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
                return userRepository.findByLogin(login)
                        .map(u -> new User(
                                u.getLogin(),
                                u.getPassword(),
                                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + u.getRole().name()))
                        )).orElseThrow(() -> new UsernameNotFoundException(login + " was not found!"));
            }
        };

    }
}
