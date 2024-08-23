package com.Controller;

import java.util.List;


import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JournalServiceImpl.JournalServicesImpl;
import com.JournalServiceImpl.UserServicesImpl;
import com.Json.JsonResponce;
import com.entity.JournalEnitiy;
import com.entity.UserEnitiy;

@RestController
@RequestMapping("/journal")
public class JournalController {
         
	@Autowired
	 private JournalServicesImpl journalServiceimpl; 
	
	@Autowired
	private UserServicesImpl userservicesimpl;
	 


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/getdata")
	public ResponseEntity<?> getAlljournalEntriesofUser()
	{
	     org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	     String username = authentication.getName();
		UserEnitiy user = userservicesimpl.findByUserName(username);
		List<JournalEnitiy> getJournal =  user.getJournalentry();
		if(getJournal != null && !getJournal.isEmpty())
		{	
			return  new ResponseEntity(getJournal,HttpStatus.OK);
		}
		return  new ResponseEntity(getJournal,HttpStatus.NOT_FOUND);
		
	}
	
	
	@PostMapping
	public ResponseEntity<?> createEntry(@RequestBody JournalEnitiy  myEntry )
	{
		try
		{
			org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			journalServiceimpl.addRecords(myEntry,username);
			return new ResponseEntity<JournalEnitiy>(myEntry,HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<JournalEnitiy>(myEntry,HttpStatus.BAD_REQUEST);
			
		}
		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JournalEnitiy> getJournalEntryById(@PathVariable ObjectId id)
	{
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		UserEnitiy userEnitiy = userservicesimpl.findByUserName(username);
		List<JournalEnitiy> collect = userEnitiy.getJournalentry().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
		if(collect.isEmpty())
		{
			Optional<JournalEnitiy> recordsbyid = journalServiceimpl.getRecordsbyid(id);
			if(recordsbyid.isPresent())
			{
				return new ResponseEntity<JournalEnitiy>(recordsbyid.get(),HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<JournalEnitiy>(HttpStatus.NOT_FOUND);
		
		
	}
	
	@DeleteMapping("/myid/{id}")
	public ResponseEntity<JournalEnitiy> deleteJournalEntrybyId(@PathVariable ObjectId id)
	{

		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		boolean removed = journalServiceimpl.deleteRecordsbyid(id,username);
		if(removed) {
			return new ResponseEntity<JournalEnitiy>(HttpStatus.NO_CONTENT);

		}else
		{
			return new ResponseEntity<JournalEnitiy>(HttpStatus.NOT_FOUND);

		}
		
	}
	@PutMapping("myid/{id}")
	public ResponseEntity<?> updatejournalentryById(@PathVariable ObjectId id , @RequestBody JournalEnitiy newentry )
	{
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		UserEnitiy userEnitiy = userservicesimpl.findByUserName(username);
		List<JournalEnitiy> collect = userEnitiy.getJournalentry().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
		if(collect.isEmpty())
		{
			Optional<JournalEnitiy> recordsbyid = journalServiceimpl.getRecordsbyid(id);
			if(recordsbyid.isPresent())
			{
			    JournalEnitiy old = recordsbyid.get();
			    old.setTittle(newentry.getTittle()!= null && !newentry.getTittle().equals("") ? newentry.getTittle() : old.getTittle());
				old.setContent(newentry.getContent()!= null && !newentry.getContent().equals("") ? newentry.getContent(): old.getContent());
				journalServiceimpl.addRecords(old);
				return new ResponseEntity<JournalEnitiy>(old,HttpStatus.OK);
			    
			}
		}
		
		return new ResponseEntity<JournalEnitiy>(HttpStatus.NO_CONTENT);
	}
}
