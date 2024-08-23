package com.JournalService;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.entity.JournalEnitiy;
import com.entity.UserEnitiy;

public interface UserServices {
	
	public UserEnitiy saveNewUser(UserEnitiy users);
	
	
	public List<UserEnitiy> getAllrecords();
	
	public void deleteRecordsbyid(ObjectId id);
	
	public void deleteAllrecords();

	public Optional<UserEnitiy> getRecordsbyid(ObjectId id);


	public  UserEnitiy findByUserName(String username);


	public void deleteUserbyName(String name);


	UserEnitiy saveAdmin(UserEnitiy users);
	



	
	

}
