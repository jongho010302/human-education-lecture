package com.myworkspace.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
	
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
		http.headers().cacheControl();		  
	    http.headers().contentTypeOptions();
	    http.headers().frameOptions();
	    http.headers().xssProtection();
	  }
}
