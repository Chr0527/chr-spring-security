package com.chryl.config.springSecurityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("SecurityConfig.configure > http");

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/manage/demo/hello").permitAll() // 此页面允许任何人访问，即使未登录
                .antMatchers("/manage/demo/info1").hasAnyRole("ADMIN") // 仅允许 ADMIN 角色的用户访问
                .antMatchers("/manage/demo/info2").hasAnyRole("USER") // 仅允许 USER 角色的用户访问
                .anyRequest().denyAll() // 其他资源禁止访问
                .and()
                .formLogin()
                .loginPage("/manage/demo/login") // 自定义登录页面
                .failureUrl("/manage/demo/error") // 登录错误页面
                .permitAll() // 允许任何用户访问
                .and()
                .logout()
                .logoutUrl("/manage/demo/exit") // 退出登录
                .logoutSuccessUrl("/manage/demo/index") // 退出登录成功返回的页面
                .permitAll() // 也允许任务用户访问
                .and()
                .exceptionHandling();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("SecurityConfig.configure > withUser");

        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin")
                .password(new BCryptPasswordEncoder()
                        .encode("123")).roles("ADMIN")

                .and()

                .withUser("user")
                .password(new BCryptPasswordEncoder()
                        .encode("123")).roles("USER");
    }

}