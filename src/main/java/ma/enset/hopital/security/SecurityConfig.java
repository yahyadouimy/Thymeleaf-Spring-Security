package ma.enset.hopital.security;
import ma.enset.hopital.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@AllArgsConstructor
@Service
public class SecurityConfig  {



   private UserDetailsServiceImpl userDetailsServiceImp;




   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.formLogin((a)->a.loginPage("/login").defaultSuccessUrl("/"));
        http.authorizeHttpRequests((auth)->auth.requestMatchers("/webjars/**").permitAll());
        http.authorizeHttpRequests((auth)->auth.requestMatchers("/login").permitAll());
        http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/login ").permitAll().anyRequest().authenticated());
        http.exceptionHandling((auth)->auth.accessDeniedPage("/notAuth"));

       http.userDetailsService(userDetailsServiceImp);


        return http.build();
    }








//   @Bean
//   public JdbcUserDetailsManager jdbcUserDetailsManagerConfigurer(DataSource dataSource){
//       return new JdbcUserDetailsManager(dataSource);
//   }







 //   @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails1 = User.withDefaultPasswordEncoder()
//                .username("user1")
//                .password("1234")
//                .roles("USER")
//                .build();
//        UserDetails userDetails2 = User.withUsername("admin")
//                .password(passwordEncoder().encode("1234"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails1,userDetails2);
//    }


//  @Bean
//    public AuthenticationManager authenticationManager(
//            UserDetailsService userDetailsService,
//            PasswordEncoder passwordEncoder) {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//        return new ProviderManager(authenticationProvider);
//    }



}
