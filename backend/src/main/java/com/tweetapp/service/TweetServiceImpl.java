package com.tweetapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tweetapp.bean.TweetAppConsolidatedRequest;
import com.tweetapp.dao.TweetsDaoImpl;
import com.tweetapp.dao.UsersDaoImpl;
import com.tweetapp.entity.Tweets;
import com.tweetapp.entity.Users;
import com.tweetapp.util.TweetAppUtil;

@Service
@Component
public class TweetServiceImpl implements TweetService {

	@Autowired
	TweetAppUtil tweetAppUtil;

	@Autowired
	UsersDaoImpl usersDaoImpl;
	
	@Autowired
	TweetsDaoImpl tweetsDaoImpl;

	public List<Tweets> getAllTweets() {
		List<Tweets> allTweets = new ArrayList<>();
		
		//get all users
		List<Users> allUsers = usersDaoImpl.findAllUsers();
		
		for (Users users : allUsers) {
			
			//extract tweets
			if(users.getTweets() != null) {
			for (Tweets tweetList : users.getTweets()) {
				allTweets.add(tweetList);
			}}
		}
		return allTweets;
		//return tweetsDaoImpl.getAllTweets();
	}

	public Boolean createNewTweet(TweetAppConsolidatedRequest req, String username) {
		Users userReceived = null;
		List<Tweets> tweets = new ArrayList<>();
		Users user = usersDaoImpl.findUserByUserId(username);
		Tweets saveTweet = new Tweets();
		saveTweet = req.getCreateNewTweet();
		saveTweet.setAuthor(user.getUserId());
		if(user.getTweets()==null) {
			tweets.add(saveTweet);
			user.setTweets(tweets);
		}
		else
		{
			user.getTweets().add(saveTweet);
		}

		userReceived = usersDaoImpl.saveTweet(user);
		//save a reference in as Tweets Document
		tweetsDaoImpl.createNewTweet(saveTweet);
		if(userReceived == null) {
			return false;
		}else {
			return true;
		}
	}

	public Boolean updateTweet(String tweet, String username, String tweetId) {
		Users user = null;
		
		user = usersDaoImpl.findUserByUserId(username);
		
		List<Tweets> tweetIterator = user.getTweets();
		
		for (Tweets iterable_element : tweetIterator) {
			if(iterable_element.get_id().equals(tweetId)) {
				iterable_element.setTweet(tweet);
			}
		}
		
		user.setTweets(tweetIterator);
		
		Users updatedTweet = usersDaoImpl.saveTweet(user);
		if(updatedTweet != null) {
			return true;
		}
		else
			return false;
		
		
		
	}

}
