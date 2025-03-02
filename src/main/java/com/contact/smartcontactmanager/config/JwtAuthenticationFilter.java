package com.contact.smartcontactmanager.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.contact.smartcontactmanager.service.JwtService;
import com.contact.smartcontactmanager.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private JwtService jwtService;

	private UserService userService;

	public JwtAuthenticationFilter(JwtService jwtService, UserService userService) {
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// check it first authfilter is null or not
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer")) {
			// token we take in String format. it will start with Bearer and key
			// Bearer 5596ghvgdkytd
			String token = authHeader.substring(7);

			// validate token here check
			if (jwtService.validateToken(token)) {
				String userNameFromToken = jwtService.getUserNameFromToken(token);
				// here we get UserDetails
				UserDetails userDetails = userService.loadUserByUsername(userNameFromToken);
				//Authentication set only when this if block will run correctly. 
				//if it is not run then authentication already there
				if (SecurityContextHolder.getContext().getAuthentication() == null) {

					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}

			}

		}
		filterChain.doFilter(request, response);

	}
	

}
