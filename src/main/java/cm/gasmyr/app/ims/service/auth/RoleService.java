package cm.gasmyr.app.ims.service.auth;

import cm.gasmyr.app.ims.core.auth.Role;
import cm.gasmyr.app.ims.service.CrudService;

public interface RoleService extends CrudService<Role>{

	Role findByName(String string);

}
