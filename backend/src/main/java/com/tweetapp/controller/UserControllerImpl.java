package com.tweetapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.bean.TweetAppConsolidatedRequest;
import com.tweetapp.bean.TweetAppConsolidatedResponse;
import com.tweetapp.constant.TweetAppConstants;
import com.tweetapp.entity.Users;
import com.tweetapp.exception.TweetAppException;
import com.tweetapp.service.UserServiceImpl;
import com.tweetapp.util.TweetAppUtil;

import io.swagger.v3.oas.annotations.Operation;

@Component
//@RequestMapping("/api/v1.0/tweets/")
@RestController
@CrossOrigin(origins= {"*"})
public class UserControllerImpl implements UserController {

	private static final Logger log = LoggerFactory.getLogger(UserControllerImpl.class);

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	TweetAppUtil tweetAppUtil;

	//save user
	@Operation(summary = "registerUser", description = "Register an user, and allow access to website")
	@PostMapping("/register")
	public ResponseEntity<TweetAppConsolidatedResponse> registerUser(@RequestBody TweetAppConsolidatedRequest req)
			throws TweetAppException {
		TweetAppConsolidatedResponse res = new TweetAppConsolidatedResponse();
		Users savedUser;
		Boolean alreadyExistsUserId = true;
		Boolean alreadyExitsEmail = true;
		try {

			// check userId Already Exists
			alreadyExistsUserId = userServiceImpl.checkIfUserAlreadyExistsByUserId(req.getSaveUser());
			alreadyExitsEmail = userServiceImpl.checkIfUserAlreadyExistsByEmail(req.getSaveUser());
			
			if (alreadyExistsUserId == false && alreadyExitsEmail == false) {
				
				savedUser = userServiceImpl.registerUser(req.getSaveUser());
				res.setMessage(
						savedUser.getEmail().toString() + " :: is Saved and loginId is :: " + savedUser.getUserId().toString());
				res.setUser(savedUser);
				res.setStatus(TweetAppConstants.SUCCESS);
			}
			else if(alreadyExistsUserId == true){
				res.setMessage("Please Retry UserId :: " +req.getSaveUser().getUserId() + " :: Already Exists");
				res.setStatus(TweetAppConstants.FAIL);
			}
			else if(alreadyExitsEmail == true){
				res.setMessage("Please Retry Email :: " +req.getSaveUser().getEmail() + " :: Already Exists");
				res.setStatus(TweetAppConstants.FAIL);
			}
		} catch (Exception es) {
			//throw new TweetAppException(TweetAppExceptionValues.TWEETAPPCODE001,
			//		TweetAppExceptionValues.TWEETAPPMESSAGE001);
			es.printStackTrace();
			res.setStatus(TweetAppConstants.FAIL);
		}
		return ResponseEntity.ok(res);
	}

	//get all users
	@Operation(summary = "loadAllUsers", description = "Fetch a list with all users from database")
	@GetMapping("users/all")
	public ResponseEntity<TweetAppConsolidatedResponse> loadAllUsers() throws TweetAppException {
		TweetAppConsolidatedResponse res = new TweetAppConsolidatedResponse();
		List<Users> allUsers = null;
		try {
			allUsers = userServiceImpl.getAllUsers();
			res.setAllUsers(allUsers);
			res.setStatus(TweetAppConstants.SUCCESS);

		} catch (Exception es) {
			res.setStatus(TweetAppConstants.FAIL);
				es.printStackTrace();
		}
		
		return ResponseEntity.ok(res);

	}
	
	@Operation(summary = "getUser", description = "Fetch a user from database with username")
	@GetMapping("users/search/{username}")
	public ResponseEntity<TweetAppConsolidatedResponse> getUser(@PathVariable("username") String username) throws TweetAppException {
		TweetAppConsolidatedResponse res = new TweetAppConsolidatedResponse();
		Users user = null;
		try {
			user = userServiceImpl.getUserById(username);
			res.setUser(user);
			res.setMessage("Saved User Bean is used to fetch User Data from database::");
			res.setStatus(TweetAppConstants.SUCCESS);
		} catch (Exception es) {
			res.setStatus(TweetAppConstants.FAIL);
			es.printStackTrace();

		}
		
		return ResponseEntity.ok(res);
	}

}
