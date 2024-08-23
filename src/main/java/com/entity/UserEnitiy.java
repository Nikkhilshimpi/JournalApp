package com.entity;


import java.util.ArrayList;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Id;
import lombok.Data;

@Document(collection = "Users")
@Data
public class UserEnitiy {
	
	@Id
	private ObjectId id;
	@Indexed
	@NonNull
	private String username;
	@NonNull
	private String password;
	@DBRef
	private List<JournalEnitiy> journalentry = new ArrayList<>();
	private List<String> roles;
	
	
	
	
	

}
