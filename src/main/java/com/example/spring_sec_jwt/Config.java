package com.example.spring_sec_jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class Config extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService userDetailsService() {

        //metoda withDefault... przechowuje login i haslo plain textem
        //normalnie to powynno byc zaszyfrowane np BCryptem (albo innym algorytmem sluzacym do szyfrowania hasel)
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user1")
                .roles("USER")
                .build();
        //ponizsze zapisuje uzytkownika w pamieci - w full wersji zapis do bazy danych
        return new InMemoryUserDetailsManager(userDetails);

    }

    //trzeba nadisac metode configure (http)/override methods /configure
    //ta metoda sprawdza czy uzytkownik ma uprawnienia wymagane do tego aby otrzymac response na zapytanie na dany URL
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorizeRequests() - incializuje analizę requestu
        http.authorizeRequests()
        //antMatchers lapie fragment adresu URL zeby sprawdzic czy są spełnione uprawnienia
        .antMatchers("/")
        /*możliwe metody to:
        permitAll - zezwala wszystkim na dostep
        authenticated - zalogowany
        hasRole = z daną rolą
        */
        .permitAll()
        //tu można zakończyć ale poniżej dodany fragment mówiący że zapytania na wszystkie inne URL już wymagają roli admina
        .anyRequest().hasRole("admin")
        ;
    }


}
