package com.tweetapp.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tweetapp.entity.Tweets;
import com.tweetapp.entity.Users;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class TweetAppConsolidatedRequest {

	Users saveUser;
	
	Tweets createNewTweet;
}
