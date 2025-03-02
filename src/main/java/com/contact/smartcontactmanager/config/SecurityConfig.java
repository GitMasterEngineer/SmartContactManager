package com.contact.smartcontactmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	private JwtAuthenticationFilter jwtAuthenticationFilter;
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	
	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
			CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(AbstractHttpConfigurer::disable)

				// Get API is the top high priority API after that another API will call
				.authorizeHttpRequests(auth
				// Everyone access this API GET request is there
				-> auth.requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
						// this API will only accessible by Admin only. hasrole is method for access api
						// for specific person. POST API is there.
						.requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
						// Another request will authenticated here
						.requestMatchers("/api/v1/**").hasAnyRole("ADMIN", "GUEST")// thode two people can access this
																					// api
						.anyRequest().permitAll()

				)
				.exceptionHandling(exception -> exception.authenticationEntryPoint(customAuthenticationEntryPoint))
				// This sessionManagement property is satateless. data will not store on server.
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// we have to create Filter. it will run first before start authentication.over
				// token is coming from use or not.
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();

	}

}
