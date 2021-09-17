package com.oyl.board.member.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .httpBasic().disable() // rest api이므로 기본설정 X, 기본설정은 비인증시 로그인폼 화면으로 redirect됨.
                .csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests() // 사용권한 체크
                        .antMatchers("/auth/signin","/auth/signup", "/post/free","/posts/free", "/auth/member", "/auth/member/**", "/post/**","/comments/**").permitAll() // 가입 및 로그인은 누구나 접근가능
                        .antMatchers(HttpMethod.GET, "/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/**").permitAll()
                        .anyRequest().hasRole("USER") // 그외 나머지 요청은 모두 인증된 회원만 접근가능
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Override // ignore check swagger resource
    public void configure(WebSecurity web){
        web.ignoring().antMatchers();
    }

}
