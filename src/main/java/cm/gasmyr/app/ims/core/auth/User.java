package cm.gasmyr.app.ims.core.auth;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import cm.gasmyr.app.ims.core.customer.AbstractCustomer;

@Entity
@Table(name = "_user")
public class User extends AbstractCustomer {

	public User() {
	}

	private String username;
	@Transient
	String rolesNames;
	@Transient
	private String password;
	@Transient
	private String passwordConfirm;
	private String encryptedpassword;
	private boolean active;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), name = "user_role")
	private Set<Role> roles = new HashSet<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getEncryptedpassword() {
		return encryptedpassword;
	}

	public void setEncryptedpassword(String encryptedpassword) {
		this.encryptedpassword = encryptedpassword;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRolesNames() {
		this.rolesNames = roles.stream().map(r -> r.getName()).collect(Collectors.joining(","));
		return rolesNames;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if (!this.roles.contains(role)) {
			this.roles.add(role);
		}
	}

	public void removeRole(Role role) {
		this.roles.remove(role);
	}

	public void updateInternal(User user) {
		super.updateInternal(user);
		this.username = user.getUsername();
		this.active = user.isActive();
		this.encryptedpassword = user.getEncryptedpassword();
		this.roles = user.getRoles();
	}

	public boolean canBeSave() {
		return username != null && username != "" && password != null && password != "" && passwordConfirm != null
				&& passwordConfirm != "" && password.equals(passwordConfirm);
	}
}
