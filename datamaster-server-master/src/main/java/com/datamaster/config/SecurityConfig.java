package com.datamaster.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/**").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/registration").permitAll()
			.antMatchers("/h2-console/**").permitAll();
		
//		http.authorizeRequests()
//			.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
//			.authenticated();
		http.authorizeRequests()
			.antMatchers("/admin/**").hasAuthority("ADMIN");
		
//		http.authorizeRequests()
//			.antMatchers("/console").access("hasRole('ADMIN','USER')");
		http.authorizeRequests()
			.antMatchers("/console/**").hasAnyAuthority("ADMIN","USER");
		
		http.authorizeRequests()
			.and().exceptionHandling()
			.accessDeniedPage("/403");
		
		http.authorizeRequests()	
			.and().formLogin()
			//.loginProcessingUrl("/j_security_check")
			.loginPage("/login").failureUrl("/login?error=true")
			.defaultSuccessUrl("/admin/home")
			.usernameParameter("email")
			.passwordParameter("password")
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/?");
			
		http.headers().frameOptions().disable();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		http.sessionManagement().maximumSessions(2);
		//http.sessionManagement().expiredUrl("/sessionExpired.html").invalidSessionUrl("/invalidSession.html");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring()
		   .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
