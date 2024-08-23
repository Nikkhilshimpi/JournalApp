package com.JournalService;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.entity.JournalEnitiy;
import com.entity.UserEnitiy;

public interface JournalServices {
	
	public void addRecords(JournalEnitiy myEntry ,String username);
	
	
	public List<JournalEnitiy> getAllrecords();
	
	
	public void deleteAllrecords();

	public Optional<JournalEnitiy> getRecordsbyid(ObjectId id);


	public boolean deleteRecordsbyid(ObjectId id, String username);


	public void addRecords(JournalEnitiy myEntry);
	
	public List<JournalEnitiy> findByUserName(String username);




	

	
	

}
