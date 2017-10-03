package cm.gasmyr.app.ims.core.auth;

import javax.persistence.Entity;
import javax.persistence.Table;

import cm.gasmyr.app.ims.core.AbstractDomainObject;

@Entity
@Table(name = "_role")
public class Role extends AbstractDomainObject {

	public Role() {
	}

	private String name;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void updateInternal(Role role) {
		this.name = role.getName();
		this.description = role.getDescription();
	}

	public boolean canBeSave() {
		return name != null && name != "";
	}

}
