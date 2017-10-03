package cm.gasmyr.app.ims.core.provider;

import javax.persistence.Entity;
import javax.persistence.Table;

import cm.gasmyr.app.ims.core.customer.AbstractCustomer;
import cm.gasmyr.app.ims.util.Utils;

@Entity
@Table(name = "provider")
public class Provider extends AbstractCustomer {
	private boolean enable;

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void updateInternal(Provider provider) {
		this.setFullName(provider.getFullName());
		this.setAddress(provider.getAddress());
		this.setEmail(provider.getEmail());
		this.setPhone(provider.getPhone());
		this.setEnable(provider.isEnable());
	}

	public boolean canBeSave() {
		return Utils.isValid(this.getFullName());
	}

}
