package cm.gasmyr.app.ims.service.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.gasmyr.app.ims.core.auth.User;
import cm.gasmyr.app.ims.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;


	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
		
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> listAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User saveOrUpdate(User userToSaveOrUpdate) {
		if (userToSaveOrUpdate.getUsername() != null) {
			if (userToSaveOrUpdate.getId()>1) {
				User user=userRepository.findOne(userToSaveOrUpdate.getId());
				user.updateInternal(userToSaveOrUpdate);
				userRepository.save(user);
			} else {
				userRepository.save(userToSaveOrUpdate);
			}
		}
		return userRepository.findByUsername(userToSaveOrUpdate.getUsername());
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

}
