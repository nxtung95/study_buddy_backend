package com.studybuddy.backend.StudentService.impl;

import com.studybuddy.backend.entity.User;
import com.studybuddy.backend.repository.UserRepository;
import com.studybuddy.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public User login(String username, String password) {
		try {
			log.info("Login with user: " + username);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if (userDetails == null) {
				return null;
			}
			if (!passwordEncoder.matches(password, userDetails.getPassword())) {
				return null;
			}
			Collection<SimpleGrantedAuthority> roleList = (Collection<SimpleGrantedAuthority>) userDetails.getAuthorities();
			return User.builder()
					.email(username)
					.role(roleList.stream().findFirst().get().getAuthority())
					.build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	@Transactional
	public boolean register(User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.saveAndFlush(user);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkExistByEmail(String mail) {
		return userRepository.findByEmail(mail).isPresent();
	}
}
