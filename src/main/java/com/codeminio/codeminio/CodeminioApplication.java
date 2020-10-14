package com.codeminio.codeminio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages="com.codeminio.dominio")
@EnableJpaRepositories(basePackages="com.codeminio.repository")
@ComponentScan(basePackages = {"com.codeminio.*"})
@EnableTransactionManagement
@EnableWebMvc
public class CodeminioApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(CodeminioApplication.class, args);
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String result = encoder.encode("admin");
//		System.out.println(result);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry reg) {
		reg.addViewController("/login").setViewName("/login");
		reg.setOrder(Ordered.LOWEST_PRECEDENCE);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**", "/css/**", "/js/**","/assets/**").addResourceLocations("classpath:/static/img/",
				"classpath:/static/css/", "classpath:/static/js/","classpath:/static/assets/");
	}

}
