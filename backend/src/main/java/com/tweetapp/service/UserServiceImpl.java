package com.tweetapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tweetapp.dao.UsersDaoImpl;
import com.tweetapp.entity.Users;
import com.tweetapp.exception.TweetAppException;

@Component
public class UserServiceImpl implements UserService{
	
	@Autowired
	UsersDaoImpl usersDaoImpl;

	public Users registerUser(Users saveUser)  throws TweetAppException{
		Users registerNewUser = new Users();
		registerNewUser = saveUser;
		Users savedUser = usersDaoImpl.registerUser(registerNewUser);
		return savedUser;
	}

	public Boolean checkIfUserAlreadyExistsByUserId(Users saveUser) {
		Users user = usersDaoImpl.findUserByUserId(saveUser.getUserId());
		if(user == null) {
			return false;
		}
		else
			return true;
	}

	public Boolean checkIfUserAlreadyExistsByEmail(Users saveUser) {
		Users user = usersDaoImpl.findUserByEmail(saveUser.getEmail());
		if(user == null) {
			return false;
		}
		else
			return true;
	}

	public List<Users> getAllUsers() {
		List<Users> allUsers;
		allUsers =  usersDaoImpl.findAllUsers();
		return allUsers;
	}

	public Users getUserById(String username) {
		return usersDaoImpl.findUserByUserId(username);
	}

}
