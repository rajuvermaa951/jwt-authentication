package com.jwt.authentication.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.authentication.dto.SignupRequest;
import com.jwt.authentication.entity.CustomUserDetails;
import com.jwt.authentication.entity.User;
import com.jwt.authentication.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepo;
	private final PasswordEncoder encoder;
	
	public CustomUserDetailsService(UserRepository userRepo, PasswordEncoder encoder)
	{
		this.userRepo=userRepo;
		this.encoder=encoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username Not found"));
		
		return new CustomUserDetails(user);
	}

	public String addUser(SignupRequest signupRequest) {
		
		 User user=new User();
		 user.setUsername(signupRequest.getUsername());
		 user.setPassword(encoder.encode(signupRequest.getPassword()));
		 user.setRole(signupRequest.getRole().replace("ROLE_", ""));
		 
		 userRepo.save(user);
		 
		
		return "User has been saved ";
	}

}
