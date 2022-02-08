package com.tweetapp.security.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tweetapp.entity.Users;
import com.tweetapp.repository.UsersRepository;

@Service
public class LoginByUserDetailsService implements UserDetailsService{

	@Autowired
	UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//repocall
		//default -- test -- test
		final Users testUserForAuthentication = usersRepository.findByUserId(username);
		
		if (testUserForAuthentication == null) {
            throw new UsernameNotFoundException(username);
        }
		
		UserDetails user = User.withUsername(testUserForAuthentication.getUserId()).password(testUserForAuthentication.getPassword()).authorities("USER").build();
        return user;
	}

}

