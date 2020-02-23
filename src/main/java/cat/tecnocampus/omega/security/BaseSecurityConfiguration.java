package cat.tecnocampus.omega.security;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class BaseSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/createUser").permitAll()
                .antMatchers("/allUsers").hasRole("ADMIN")
                .antMatchers("/profile/users/**").permitAll()
                .antMatchers("/registrarAnimal").authenticated()
                .antMatchers("/animal/apadrinado/**").authenticated()
                .antMatchers("/users/{username}").authenticated()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/users/{username}")

                .and()
                .httpBasic()
                .and()

                .rememberMe()
                .tokenValiditySeconds(241920)

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll();

        http
                .csrf().disable()
                .headers()
                .frameOptions().disable();
                /*.and()
                .cors();*/
    }
}
