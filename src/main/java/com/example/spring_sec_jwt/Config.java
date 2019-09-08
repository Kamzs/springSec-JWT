package com.example.spring_sec_jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

        //todo podpiac szyfrowanie JASYPT
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user1")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin1")
                .roles("ADMIN")
                .build();

        UserDetails moderator = User.withDefaultPasswordEncoder()
                .username("mod")
                .password("mod1")
                .roles("MOD")
                .build();


        //todo podpiac baze danych
        return new InMemoryUserDetailsManager(user, admin, moderator);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //zeby mozna bylo zalogowac sie za pomoca loginu i hasla przez klienat zewnetrznego trzeba wlaczyc httpBasic
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/cytaty").permitAll()
                .antMatchers(HttpMethod.POST, "/cytaty").hasRole("MOD")
                .antMatchers(HttpMethod.DELETE, "/cytaty").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                //ponizsze po to zeby mozna bylo postmanem przesylac credentiale
                //bez wylaczenia csrf zeden zewnetrzny klient (tj. inny niz przegladrka na danym url nie moze przesylac credntiali
                //to jest zabezpieczenie przed kradzieza cisteczek
                .and().csrf().disable()
        ;

        //po logowaniu spring security wysyła do przegladrki ciasteczko
        //dostep do cookies F12
    }


}
