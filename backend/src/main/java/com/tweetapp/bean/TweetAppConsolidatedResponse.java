package com.tweetapp.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tweetapp.entity.Tweets;
import com.tweetapp.entity.Users;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class TweetAppConsolidatedResponse {
	
	private String message;
	
	private Users user;
	
	private List<Tweets> tweets;
	
	private Boolean createdTweet;
	
	private List<Users> allUsers;
	
	private String status;

}
