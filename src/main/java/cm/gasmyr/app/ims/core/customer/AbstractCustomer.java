package cm.gasmyr.app.ims.core.customer;

import javax.persistence.MappedSuperclass;

import cm.gasmyr.app.ims.core.AbstractDomainObject;

@MappedSuperclass
public class AbstractCustomer extends AbstractDomainObject {
	private String fullName;
	private String address;
	private String email;
	private String phone;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void updateInternal(AbstractCustomer user) {
		this.fullName = user.getFullName();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.address = user.getAddress();
	}
}
