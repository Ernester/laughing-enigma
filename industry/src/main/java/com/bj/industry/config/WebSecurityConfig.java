package com.bj.industry.config;

import com.bj.industry.security.AuthFilter;
import com.bj.industry.security.LoginFailHandler;
import com.bj.industry.security.LoginUrlEntryPoint;
import com.bj.industry.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * prePostEnabled 允许进入页面方法前检验
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .successForwardUrl("/industry/home")
/*                //配置角色登录处理入口
                .loginProcessingUrl("/login")
                .failureHandler(authFailHandler())*/
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/page")
                .deleteCookies("JSESSIONID")
                //设置session失效
                .invalidateHttpSession(false)
                .and().exceptionHandling()
                .authenticationEntryPoint(loginUrlEntryPoint())
                .accessDeniedPage("/403");

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 登录失败调度
     * @return
     */
    private LoginFailHandler loginFailHandler() {
        return new LoginFailHandler(loginUrlEntryPoint());
    }

    /**
     * 登录入口
     * @return
     */
    private LoginUrlEntryPoint loginUrlEntryPoint() {
        return new LoginUrlEntryPoint("/login");
    }

    /**
     * 加密算法
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder managerBuilder) throws Exception {
//        managerBuilder.inMemoryAuthentication().withUser("admin").password( passwordEncoder().encode("admin")).roles("ADMIN");
        managerBuilder.userDetailsService(userService());
       // 设置为true清除密码等信息
        managerBuilder.authenticationProvider(authenticationProvider()).eraseCredentials(true);
    }

  @Bean
  public AuthenticationProvider authenticationProvider() {
       DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
       authenticationProvider.setUserDetailsService(userService());
       authenticationProvider.setPasswordEncoder(passwordEncoder());
       return authenticationProvider;
    }

    @Bean
    public AuthFilter authFilter() throws Exception {
        AuthFilter authFilter = new AuthFilter();
        authFilter.setAuthenticationManager(authenticationManager());
        authFilter.setAuthenticationFailureHandler(loginFailHandler());
        return authFilter;
    }

    @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl();
    }
}
