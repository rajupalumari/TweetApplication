package com.tweetapp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.entity.Users;

@Repository
public interface UsersRepository extends MongoRepository<Users, ObjectId>{

	Users findByUserId(String userId);

	Users findByEmail(String userId);


}
