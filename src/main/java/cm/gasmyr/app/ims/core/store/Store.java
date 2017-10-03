package cm.gasmyr.app.ims.core.store;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.core.auth.User;
import cm.gasmyr.app.ims.core.shop.Shop;

@Entity
@Table(name = "store")
public class Store extends AbstractDomainObject {
	private String name;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "storekeeper_id", referencedColumnName = "id")
	private User storekeeper;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "shop_id", referencedColumnName = "id")
	private Shop shop;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getStorekeeper() {
		return storekeeper;
	}

	public void setStorekeeper(User storekeeper) {
		this.storekeeper = storekeeper;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
		this.shop.addStore(this);
	}

	public void updateInternal(Store store) {
		this.name = store.getName();
		this.shop = store.getShop();
		this.storekeeper = store.getStorekeeper();
	}

	public boolean canBeSave() {
		return this.shop != null && this.name != null && this.name != "";
	}

}
