package com.example.demo.config;









import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements  WebMvcConfigurer {

	/*@Autowired
	LoginService service;*/
	@Autowired
	DataSource dataSource;
	 
	 @Bean
	 PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 System.out.println("チェーンにいる");
		 http.formLogin(login -> login
				 .loginPage("/index") .permitAll()
				 .loginProcessingUrl("/login")
				 .defaultSuccessUrl("/login", true)//trueがないとcssなど静的コンテンツに遷移する
				 .failureUrl("/login?error")
			).logout(logout -> logout
				.logoutSuccessUrl("/login?logout").permitAll()
			).authorizeHttpRequests(authz -> authz
				.antMatchers("/img/**", "/css/**", "/js/**", "/h2-console/**").permitAll()
				.mvcMatchers("/index", "/login").permitAll()
				.mvcMatchers("/main", "/regist", "/update", "/delete").hasRole("USER")
				.mvcMatchers("/admin").hasRole("ADMIN")
				.anyRequest()
				.authenticated()
			);
		 http.csrf().disable();	// H2DBデバッグ用
		 http.headers().frameOptions().disable(); // H2DBデバッグ用
		 return http.build();
	 }
	 
	 /*@Bean
	 public UserDetailsService users() {
	 	UserDetails user = User.builder()
	 		.username("user")
	 		.password(passwordEncoder().encode("password"))
	 		.roles("USER")
	 		.build();
	 	return new InMemoryUserDetailsManager(user);
	 }*/
	 /*@Bean
	 DataSource dataSource_over() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
		 	.setType(EmbeddedDatabaseType.H2)
		 	.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
		 	.build();
		 return  db;
	 }*/
	 
	 @Bean
	 public UserDetailsManager userDetailsService() {
		 /*UserDetails user = User.builder()
			 		.username("user")
			 		.password(passwordEncoder().encode("password"))
			 		.roles("USER")
			 		.build();*/
		 JdbcUserDetailsManager users = new JdbcUserDetailsManager(this.dataSource);
		// users.createUser(user);
		 return users;
	 }
}
