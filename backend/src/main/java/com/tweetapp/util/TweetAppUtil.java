package com.tweetapp.util;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TweetAppUtil {

	public String generateUserId(String email, String userId) {
		
		int unique_id= (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE); 
		
		return email.substring(0 , 3) +"@"+ userId.substring(0, 3) +"#"+ unique_id;
	}

	public String generateTweetId(String tweet, String username) {
		int unique_id= (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
		return tweet.substring(0 , 3) +"@"+ username.substring(0, 3) +"#"+ unique_id;
	}

}
