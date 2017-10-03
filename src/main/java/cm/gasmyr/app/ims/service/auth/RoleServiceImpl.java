package cm.gasmyr.app.ims.service.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.gasmyr.app.ims.core.auth.Role;
import cm.gasmyr.app.ims.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	final RoleRepository roleRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public List<Role> listAll() {
		return (List<Role>) roleRepository.findAll();
	}

	@Override
	public Role getById(Long id) {
		return roleRepository.findOne(id);
	}

	@Override
	public Role saveOrUpdate(Role roleToSaveOrUpdate) {
		Role role = roleToSaveOrUpdate;
		if (roleToSaveOrUpdate.getId() != null) {
			role = roleRepository.findOne(roleToSaveOrUpdate.getId());
			if (role != null) {
				role.updateInternal(roleToSaveOrUpdate);
			}
		}
		return roleRepository.save(role);
	}

	@Override
	public void delete(Long id) {
		roleRepository.delete(id);
	}

	@Override
	public Role findByName(String roleName) {
		return roleRepository.findByName(roleName);
	}

}
