package cm.gasmyr.app.ims.service.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.auth.User;
@Component
public class UserToUserDetails implements Converter<User, UserDetails> {

	@Override
	public UserDetails convert(User user) {
		UserDetailsImpl userDetails = new UserDetailsImpl();

		if (user != null) {
			userDetails.setUsername(user.getUsername());
			userDetails.setPassword(user.getEncryptedpassword());
			userDetails.setEnabled(user.isActive());
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			user.getRoles().forEach(role -> {
				authorities.add(new SimpleGrantedAuthority(role.getName()));
			});
			userDetails.setAuthorities(authorities);
		}

		return userDetails;
	}

}
