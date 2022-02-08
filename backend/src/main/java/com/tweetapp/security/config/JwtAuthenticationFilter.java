package com.tweetapp.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tweetapp.security.service.LoginByUserDetailsService;
import com.tweetapp.util.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtutil;

	@Autowired
	private LoginByUserDetailsService loginByUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// get jwt
		// Bearer
		// validate
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);

			try {
				username = this.jwtutil.getUsernameFromToken(jwtToken);
				

			} catch (Exception es) {
				es.printStackTrace();
			}

			// fine...
			// security
			UserDetails userDetails = this.loginByUserDetailsService.loadUserByUsername(username);

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						username, jwtToken, null);
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
				
			} else {
				System.out.println("Token is Not valid");
			}

		}
		
		
		filterChain.doFilter(request, response);
	}

}
