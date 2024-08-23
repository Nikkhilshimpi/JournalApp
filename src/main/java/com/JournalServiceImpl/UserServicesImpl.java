package com.JournalServiceImpl;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.JournalRepository.UserRepo;
import com.JournalService.UserServices;
import com.entity.UserEnitiy;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	private UserRepo userRepository;
	
	private final PasswordEncoder passwordencoder = new BCryptPasswordEncoder();

	@Override
	public UserEnitiy saveNewUser(UserEnitiy users) {
		users.setPassword(passwordencoder.encode(users.getPassword()));
		users.setRoles(Arrays.asList("USSER"));
		return userRepository.save(users);
	}
	@Override
	public UserEnitiy saveAdmin(UserEnitiy users) {
		users.setPassword(passwordencoder.encode(users.getPassword()));
		users.setRoles(Arrays.asList("USSER", "ADMIN"));
		return userRepository.save(users);
	}
	
	public UserEnitiy saveUser(UserEnitiy user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<UserEnitiy> getRecordsbyid(ObjectId id) {
		return userRepository.findById(id);
	}

	@Override
	public List<UserEnitiy> getAllrecords() {
		return userRepository.findAll();
	}

	@Override
	public void deleteAllrecords() {
		userRepository.deleteAll();
	}

	@Override
	public void deleteRecordsbyid(ObjectId id) {

		userRepository.deleteById(id);

	}

	@Override
	public UserEnitiy findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void deleteUserbyName(String name) {
		// TODO Auto-generated method stub
		
	}


}
