package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JournalServiceImpl.UserServicesImpl;
import com.entity.UserEnitiy;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserServicesImpl userserviceimpl;
	
	@GetMapping("/all_users")
	public ResponseEntity<?> getallUsers()
	{
		List<UserEnitiy> allrecords = userserviceimpl.getAllrecords();
		if(allrecords != null && !allrecords.isEmpty())
		{
			return new ResponseEntity<>(allrecords,HttpStatus.OK);
		}else
		{
			return new ResponseEntity<>(allrecords,HttpStatus.NOT_FOUND);

		}
	}
	
	@PostMapping("/create-admin-user")
	public void createUser(@RequestBody UserEnitiy user)
	{
		userserviceimpl.saveAdmin(user);
		
	}

}
