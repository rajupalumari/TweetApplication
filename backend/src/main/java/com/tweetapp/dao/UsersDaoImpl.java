package com.tweetapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tweetapp.constant.TweetAppExceptionValues;
import com.tweetapp.entity.Tweets;
import com.tweetapp.entity.Users;
import com.tweetapp.exception.TweetAppException;
import com.tweetapp.repository.TweetsRepository;
import com.tweetapp.repository.UsersRepository;

@Component
public class UsersDaoImpl implements UsersDao{
	
	@Autowired
	UsersRepository userRepository;
	
	@Autowired
	TweetsRepository tweetsRepository;

	public Users registerUser(Users saveUser) throws TweetAppException {
		Users savedUser = null;
		List<Tweets> saveTweetForReference = new ArrayList<Tweets>();
		Tweets saveTweetJugad = new Tweets();
		saveTweetForReference.add(saveTweetJugad);
		try {
			saveTweetForReference = tweetsRepository.saveAll(saveTweetForReference);
		savedUser = userRepository.save(saveUser);
		}
		catch(Exception es) {
			es.printStackTrace();
			throw new TweetAppException(TweetAppExceptionValues.TWEETAPPCODEDB001, TweetAppExceptionValues.TWEETAPPMESSAGEDB001);
		}
		return savedUser;
	}

	public Users findUserByUserId(String userId) {
		Users user = null;
		user = userRepository.findByUserId(userId);
		return user;
	}

	public Users findUserByEmail(String email) {
		Users user = null;
		user = userRepository.findByEmail(email);
		System.out.println("what happened");
		return user;
	}

	public List<Users> findAllUsers() {
		List<Users> allUsers = null;
		try {
		allUsers = userRepository.findAll();}
		catch(Exception es) {
			es.printStackTrace();
		}
		return allUsers;
	}

	public Users saveTweet(Users user) {
		Users saveUser = null;
		try {
			saveUser = userRepository.save(user);
		System.out.println();
		}catch(Exception es) {
			es.printStackTrace();
		}
		return saveUser;
	}
}
