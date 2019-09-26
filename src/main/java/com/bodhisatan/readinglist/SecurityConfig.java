package com.bodhisatan.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// 自动覆盖配置的显式安全配置
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 要求登录者有READER角色
                // "/"对应Controller映射路径，此请求只有READER登录才有用，其他请求路径则开放权限
                .antMatchers("/").access("hasRole('READER')")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                // 登录表单的路径
                .loginPage("/login")
                .failureUrl("/login?error=true");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                        return readerRepository.getOne(username);
                    }
                });
    }
}
