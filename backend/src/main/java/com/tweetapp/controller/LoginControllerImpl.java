package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.bean.TweetAppConsolidatedRequest;
import com.tweetapp.bean.TweetAppConsolidatedResponse;
import com.tweetapp.constant.TweetAppConstants;
import com.tweetapp.constant.TweetAppExceptionValues;
import com.tweetapp.entity.Users;
import com.tweetapp.exception.TweetAppException;
import com.tweetapp.service.LoginServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@Component
@CrossOrigin(origins= {"*"})
public class LoginControllerImpl implements LoginController {
	@Autowired
	private LoginServiceImpl loginServiceImpl;

	// can not receive this rest api call without being logged-in
	@Operation(summary = "resetPassword", description = "Resets password, if user have userid and email")
	@PostMapping("/forgot")
	public ResponseEntity<TweetAppConsolidatedResponse> resetPassword(@RequestBody TweetAppConsolidatedRequest req)
			throws TweetAppException {

		// logged in user only require email to reset password
		TweetAppConsolidatedResponse res = new TweetAppConsolidatedResponse();
		Users resetPassword;
		try {
			resetPassword = loginServiceImpl.resetPassword(req.getSaveUser().getEmail(),
					req.getSaveUser().getPassword());
			res.setMessage("Password has been reset, for email :: " + resetPassword.getEmail()
			+ ", Please use this next time to Login.");
	res.setUser(resetPassword);
	res.setStatus(TweetAppConstants.SUCCESS);
		} catch (Exception es) {
			res.setStatus(TweetAppConstants.FAIL);
			throw new TweetAppException(TweetAppExceptionValues.TWEETAPPCODE001,
					TweetAppExceptionValues.TWEETAPPMESSAGE001);
		}
		
		
		return ResponseEntity.ok(res);
	}

}
