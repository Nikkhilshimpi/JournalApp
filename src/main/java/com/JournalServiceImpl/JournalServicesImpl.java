package com.JournalServiceImpl;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.JournalRepository.JournalRepo;
import com.JournalRepository.UserRepo;
import com.JournalService.JournalServices;
import com.entity.JournalEnitiy;
import com.entity.UserEnitiy;

import jakarta.transaction.Transactional;

@Service
public class JournalServicesImpl implements JournalServices {

	@Autowired
	private JournalRepo journalRepository;

	@Autowired
	private UserServicesImpl userServicesImpl;

	@Override
	@Transactional
	public void addRecords(JournalEnitiy myEntry, String username) {

		try {
			UserEnitiy user = userServicesImpl.findByUserName(username);
			JournalEnitiy saved = journalRepository.save(myEntry);
			user.getJournalentry().add(saved);
			userServicesImpl.saveUser(user);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException(" An error Accured Save the Entry", e);
		}
	}

	@Override
	public void addRecords(JournalEnitiy myEntry) {
		journalRepository.save(myEntry);
	}

	@Override
	public Optional<JournalEnitiy> getRecordsbyid(ObjectId id) {
		return journalRepository.findById(id);
	}

	@Override
	public List<JournalEnitiy> getAllrecords() {
		return journalRepository.findAll();
	}

	@Override
	public void deleteAllrecords() {
		journalRepository.deleteAll();
	}

	@Override
	@Transactional
	public boolean deleteRecordsbyid(ObjectId id, String username) {
		boolean removed = false;
		try {
			UserEnitiy user = userServicesImpl.findByUserName(username);
			 removed = user.getJournalentry().removeIf(x -> x.getId().equals(id));
			if(removed) {
				userServicesImpl.saveUser(user);
				journalRepository.deleteById(id);

			}
		}catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("An error accured while deleting the entry",e);
		}
		return removed;
	
	}

	@Override
	public List<JournalEnitiy> findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
