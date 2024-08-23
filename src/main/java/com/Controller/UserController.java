package com.Controller;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JournalService.UserServices;
import com.JournalServiceImpl.JournalServicesImpl;
import com.Json.JsonResponce;
import com.entity.JournalEnitiy;
import com.entity.UserEnitiy;

@RestController
@RequestMapping("/user")
public class UserController {
         
	@Autowired
	 private UserServices userServices; 
	 

	
	
	@PutMapping
	public ResponseEntity<UserEnitiy> updateusers(@RequestBody UserEnitiy user )
	{
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		UserEnitiy userInDb = userServices.findByUserName(username);
			userInDb.setUsername(user.getUsername());
			userInDb.setPassword(user.getPassword());
			userServices.saveNewUser(userInDb);
	
	    return new ResponseEntity<UserEnitiy>(HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<?> deleteUserbyid()
	{
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userServices.deleteUserbyName(authentication.getName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}

}
