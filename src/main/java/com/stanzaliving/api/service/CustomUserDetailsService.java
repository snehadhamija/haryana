package com.stanzaliving.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.model.User;
import com.stanzaliving.api.model.UserProfile;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	// @Autowired
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
		User user = userService.findByMobileNumber(mobileNumber);
		if (user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		if (user.getPassword() == null || user.getPassword().equals("")) {
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getMobileNumber(),
					passwordEncoder.encode(
							user.getFullName().substring(0, 4).toUpperCase() + user.getMobileNumber().substring(0, 4)),
					user.getState().equals("Active"), true, true, true, getGrantedAuthorities(user));
			return userDetails;
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getMobileNumber(),
				user.getPassword(), user.getState().equals("Active"), true, true, true, getGrantedAuthorities(user));
		return userDetails;
	}

	@Transactional
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (UserProfile userProfile : user.getUserProfiles()) {
			System.out.println("UserProfile : " + userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
		}
		System.out.print("authorities :" + authorities);
		return authorities;
	}

}
