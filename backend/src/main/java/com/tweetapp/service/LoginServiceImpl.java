package com.tweetapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.UsersDaoImpl;
import com.tweetapp.entity.Users;
import com.tweetapp.exception.TweetAppException;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private UsersDaoImpl usersDaoImpl;
	
	public Users resetPassword(String email, String newPassword) throws TweetAppException {
		Users resetPasswordUser = null;
		resetPasswordUser = usersDaoImpl.findUserByEmail(email);
		resetPasswordUser.setPassword(newPassword);
		resetPasswordUser = usersDaoImpl.registerUser(resetPasswordUser);
		return resetPasswordUser;
	}

}
