package com.tweetapp.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tweetapp.security.model.JwtRequest;
import com.tweetapp.security.model.JwtResponse;
import com.tweetapp.security.service.LoginByUserDetailsService;
import com.tweetapp.util.JWTUtil;

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private JWTUtil jwtutil;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private LoginByUserDetailsService loginByUserDetailsService;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println(jwtRequest.toString());
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		} catch (UsernameNotFoundException es) {
			es.printStackTrace();
			throw new Exception("Bad Username pass");
			
		} catch(BadCredentialsException es) {
			es.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		
		//fine area..
		
		UserDetails userDetails = this.loginByUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtutil.generateToken(userDetails);
		System.out.println("JWT ::" + token);
		
		//{"token":"value"}
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
