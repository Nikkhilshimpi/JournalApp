package com.JournalRepository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.entity.JournalEnitiy;
import com.entity.UserEnitiy;

@Repository
public interface JournalRepo  extends  MongoRepository<JournalEnitiy, ObjectId>{

	


}
