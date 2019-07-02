package com.chryl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created By Chr on 2019/7/2.
 */
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 注入 Security 属性类配置
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 重写PasswordEncoder  接口中的方法，实例化加密策略
     *
     * @return 返回 BCrypt 加密策略
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入 自定义的  登录处理类handler
     */
//    @Autowired
//    private MyAuthenticationSuccessHandler mySuccessHandler;
//    @Autowired
//    private MyAuthenticationFailHandler myFailHandler;
    //
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //basic 登录方式
//      http.httpBasic()

        //表单登录 方式
        http.formLogin()          // 定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login")      // 设置登录页面
                .loginProcessingUrl("/user/login") // 自定义的登录接口
//                .successHandler(mySuccessHandler)//登陆成功的处理
//                .failureHandler(myFailHandler)//登录失败的处理
                .and()
                .authorizeRequests()    // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/user/login").permitAll()   ///不需要权限认证的url,设置所有人都可以访问登录页面
                .anyRequest()        // 任何请求,登录后可以访问
                .authenticated()//其他请求都要验证
                .and()
                .logout().permitAll()//允许注销
                .and()
                .formLogin()//允许表单登录
                .and()
                .csrf().disable();     // 关闭csrf防护
    }
}

