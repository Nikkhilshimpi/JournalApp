package com.Controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/public")
public class PublicController {
         
	@Autowired
	 private UserServices userServices; 
	 
	
	
	@PostMapping("/create-user")
	public UserEnitiy saveusers(@RequestBody UserEnitiy users)
	{
		return userServices.saveNewUser(users);
	}
	
	

}
