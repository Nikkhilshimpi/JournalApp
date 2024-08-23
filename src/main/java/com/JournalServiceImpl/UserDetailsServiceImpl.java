package com.JournalServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.JournalRepository.UserRepo;
import com.entity.UserEnitiy;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired 
	private UserRepo userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEnitiy user = userrepo.findByUsername(username);
		if(user != null)
		{
			  @SuppressWarnings("unused")
			UserDetails userdetails = User.builder()
			.username(user.getUsername())
			.password(user.getPassword())
			.roles(user.getRoles().toArray(new String[0]))
			.build();
			
		}
		throw new  UsernameNotFoundException("USername not found by user"+username);
		 
	}

}
