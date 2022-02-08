package com.tweetapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.tweetapp.entity.Tweets;
import com.tweetapp.exception.TweetAppException;
import com.tweetapp.repository.TweetsRepository;

@RepositoryRestController
public class TweetsDaoImpl implements TweetsDao {

	@Autowired
	private TweetsRepository tweetsRepository;

	public Tweets getTweet(String tweetId) {
		Optional<Tweets> tweet = null;
		try {
			tweet = tweetsRepository.findById(tweetId);
		}
		catch(Exception es) {
			es.printStackTrace();
		}
		return tweet.orElseThrow();
	}
	
	public List<Tweets> getAllTweets() {
		List<Tweets> allTweets;

		allTweets = tweetsRepository.findAll();

		return allTweets;
	}

	public Tweets createNewTweet(Tweets newTweet) {
		Tweets createdTweet = tweetsRepository.save(newTweet);
		System.out.println(newTweet.getTweet());
		return createdTweet;

	}

}
