package com.tweetapp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.entity.Tweets;

@Repository
public interface TweetsRepository extends MongoRepository<Tweets, String>{

}
