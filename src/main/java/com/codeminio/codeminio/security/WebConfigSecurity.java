package com.codeminio.codeminio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ImplementacaoUserDetailsService implementacaoUser;

	@Override
	protected void configure(HttpSecurity http) throws Exception{ // configura as solicitações de acesso por http
		http.csrf()
		.disable() //desativa as configurações padrão de memória
		.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/").permitAll()//qualquer usuario acessa a pagina inicial
		.antMatchers(HttpMethod.GET,"/index/**").permitAll()//qualquer usuario acessa a pagina inicial
		.antMatchers(HttpMethod.GET,"/funcionario/**").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/sistema/index").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and().formLogin()
		.permitAll() // permite qualquer usuario
		.loginPage("/login")
		.defaultSuccessUrl("/sistema/index")
		.failureUrl("/login?error=true")
		.and().logout()//mapeia url de logout e invalida usuario autenticado
		.logoutSuccessUrl("/")
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(implementacaoUser).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	protected String pageLoginSucess() {
		return "/index";
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected String pageLogoutSucess() {
		return "/login";
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/assets/**");
	}
}
