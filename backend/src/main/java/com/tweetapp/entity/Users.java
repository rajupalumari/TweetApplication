package com.tweetapp.entity;

import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.OneToMany;
import lombok.Data;

@Data
@Document("_users_DB")
public class Users {
	private String firstName;
	private String lastName;
	
	@JsonIgnore
	@Id
	private String _id;
	
	private String email;
	private String userId;//loginId//username
	private String password;
	private Long contactNumber;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Tweets> tweets;
	
	public Users() {
		this._id = UUID.randomUUID().toString();
    }
}
