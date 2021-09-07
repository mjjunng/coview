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

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/assets/**", "/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // 5
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/create_account", "/h2-console/**").permitAll() // 누구나 접근 허용
                .antMatchers("/dashboard", "/coview").authenticated() // 로그인해야 접근가능
                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/login") // 로그인 페이지 링크
                .defaultSuccessUrl("/dashboard") // 로그인 성공 후 리다이렉트 주소
                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true) // 세션 날리기
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
        auth.userDetailsService(memberService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }



}
