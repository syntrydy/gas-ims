package cm.gasmyr.app.ims.core.customer;

import javax.persistence.Entity;
import javax.persistence.Table;

import cm.gasmyr.app.ims.util.Utils;

@Entity
@Table(name = "customer")
public class Customer extends AbstractCustomer {
	private boolean trustable;

	public boolean isTrustable() {
		return trustable;
	}

	public void setTrustable(boolean trustable) {
		this.trustable = trustable;
	}

	public void updateInternal(Customer customer) {
		this.setFullName(customer.getFullName());
		this.setAddress(customer.getAddress());
		this.setEmail(customer.getEmail());
		this.setPhone(customer.getPhone());
		this.setTrustable(customer.isTrustable());
	}

	public boolean canBeSave() {
		return Utils.isValid(this.getFullName());
	}
}
