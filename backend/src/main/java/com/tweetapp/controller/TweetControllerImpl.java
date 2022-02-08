package com.tweetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.bean.TweetAppConsolidatedRequest;
import com.tweetapp.bean.TweetAppConsolidatedResponse;
import com.tweetapp.constant.TweetAppConstants;
import com.tweetapp.entity.Tweets;
import com.tweetapp.exception.TweetAppException;
import com.tweetapp.service.TweetServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@Component
@CrossOrigin(origins= {"*"})
public class TweetControllerImpl implements TweetController {

	@Autowired
	private TweetServiceImpl tweetServiceImpl;

	//get all tweets
	@Operation(summary = "loadAllTweets", description = "Fetch list with all the Tweets from database")
	@GetMapping("tweets/all")
	public ResponseEntity<TweetAppConsolidatedResponse> loadAllTweets() throws TweetAppException {
		TweetAppConsolidatedResponse res = new TweetAppConsolidatedResponse();
		List<Tweets> tweetList = null;
		try {
			tweetList = tweetServiceImpl.getAllTweets();
			res.setTweets(tweetList);
			res.setStatus(TweetAppConstants.SUCCESS);

		} catch (Exception es) {
			res.setStatus(TweetAppConstants.FAIL);
			es.printStackTrace();

		}
		
		return ResponseEntity.ok(res);

	}

	//create new tweet
	@Operation(summary = "addTweet", description = "Create a new Tweet for an User")
	@PostMapping("{username}/add")
	public ResponseEntity<TweetAppConsolidatedResponse> addTweet(@RequestBody TweetAppConsolidatedRequest req,
			@PathVariable("username") String username) throws TweetAppException {
		TweetAppConsolidatedResponse res = new TweetAppConsolidatedResponse();
		Boolean tweetCreated = false;
		try {
			
			tweetCreated = tweetServiceImpl.createNewTweet(req, username);
			res.setCreatedTweet(tweetCreated);
			res.setStatus(TweetAppConstants.SUCCESS);

		} catch (Exception es) {
			res.setStatus(TweetAppConstants.FAIL);
			es.printStackTrace();
		}
		
		return ResponseEntity.ok(res);
	}
	
	//updateTweet
	@Operation(summary = "updateTweet", description = "Update Tweet")
	@PutMapping("{username}/update/{id}")
	public ResponseEntity<TweetAppConsolidatedResponse> updateTweet(@RequestBody TweetAppConsolidatedRequest req,
			@PathVariable("username") String username, @PathVariable("id") String tweetId) throws TweetAppException {
		TweetAppConsolidatedResponse res = new TweetAppConsolidatedResponse();
		Boolean tweetUpdated = false;
		try {
			tweetUpdated = tweetServiceImpl.updateTweet(req.getCreateNewTweet().getTweet(), username, tweetId);
			res.setCreatedTweet(tweetUpdated);
			res.setStatus(TweetAppConstants.SUCCESS);
		} catch (Exception es) {
			res.setStatus(TweetAppConstants.FAIL);
			es.printStackTrace();
		}
		
		return ResponseEntity.ok(res);
	}
	
	
}
