package cm.gasmyr.app.ims.service.auth;

import cm.gasmyr.app.ims.core.auth.User;
import cm.gasmyr.app.ims.service.CrudService;

public interface UserService extends CrudService<User> {

	User findByUsername(String username);

	User findByEmail(String email);
}
