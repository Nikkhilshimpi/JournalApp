package com.entity;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "testcollection")
@Data
@NoArgsConstructor
public class JournalEnitiy {
	
	@Id
	@NonNull
	private ObjectId id;
	private String tittle;
	private String content;
	
	
	
	
	

}
