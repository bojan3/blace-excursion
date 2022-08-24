package com.blace.excursion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.blace.excursion.security.auth.RestAuthenticationEntryPoint;
import com.blace.excursion.security.auth.TokenAuthenticationFilter;
import com.blace.excursion.service.impl.CustomUserDetailsService;
import com.blace.excursion.util.TokenUtils;

@Configuration
// Ukljucivanje podrske za anotacije "@Pre*" i "@Post*" koje ce aktivirati autorizacione provere za svaki pristup metodi
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije (10 rundi)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(customUserDetailsService) 
			.passwordEncoder(passwordEncoder());
	}

	@Autowired
	private TokenUtils tokenUtils;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

			.authorizeRequests().antMatchers("/auth/**").permitAll()
								.antMatchers("/api/excursion/**").permitAll()
								.antMatchers("/api/whoami").permitAll()
								.antMatchers("/api/tourguide/**").hasRole("TOURGUIDE")
								.antMatchers("/api/client/**").hasRole("CLIENT")
								.antMatchers("/api/admin/**").permitAll()   //.hasRole("ADMIN")
			.anyRequest().authenticated().and()
			
			// za development svrhe ukljuci konfiguraciju za CORS iz WebConfig klase
			.cors().and()

			// custom filter umesto TokenAuthenticationFilter kako bi se vrsila provera JWT tokena umesto cistih korisnickog imena i lozinke (koje radi BasicAuthenticationFilter)
			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, customUserDetailsService), BasicAuthenticationFilter.class);
		
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		 web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
		 
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
	}

}
