package com.tweetapp.entity;

import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Document("_tweets_DB")
public class Tweets {

	private String tweet;

	private String author;

	@Id
	private String _id;
	
	public Tweets() {
        this._id = UUID.randomUUID().toString();
    }

}
