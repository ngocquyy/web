package spring.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring.entity.Accounts;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Accounts account = accountService.findByUserName(username);
		System.out.println("Account= " + account);

		if (account == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		String role = account.getUserRole();

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

		// ROLE_Admin
		GrantedAuthority authority = new SimpleGrantedAuthority(role);

		grantList.add(authority);

		boolean enabled = account.isActive();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		UserDetails userDetails = (UserDetails) new User(account.getUserName(), account.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, grantList);

		return userDetails;
	}
	
}
