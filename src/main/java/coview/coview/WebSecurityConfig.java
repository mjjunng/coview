package coview.coview;

import coview.coview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.net.http.WebSocket;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final MemberService memberService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/assets/**", "/h2-console/**",
                "/signal", "/signal2", "/test");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // 5
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/create_account", "/h2-console/**", "/signal"
                        , "/signal2", "/test").permitAll() // 누구나 접근 허용
                .antMatchers("/dashboard", "/coview").authenticated() // 로그인해야 접근가능
                .anyRequest().authenticated()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
        auth.userDetailsService(memberService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }



}
