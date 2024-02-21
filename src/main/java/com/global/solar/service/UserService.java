package com.global.solar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.global.solar.model.Role;
import com.global.solar.model.User;
import com.global.solar.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User dbUsername = userRepository.findByUsername(username);

		if(dbUsername==null)
			throw new UsernameNotFoundException("Invalid Username or Password");
		
		UserDetails user =new org.springframework.security.core.userdetails.User(
				dbUsername.getUsername(),encoder.encode(dbUsername.getPassword()),dbUsername.getAuthorities());
		
		System.out.println("user="+user.getUsername()+ "pass="+user.getPassword()+
				"auth="+user.getAuthorities());
		return user;

	}
	
}


