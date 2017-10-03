package cm.gasmyr.app.ims.core.shop;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.core.auth.User;
import cm.gasmyr.app.ims.core.pointofsale.PointOfSale;
import cm.gasmyr.app.ims.core.store.Store;

@Entity
@Table(name = "shop")
public class Shop extends AbstractDomainObject {

	private String name;
	private String phone;
	private String website;
	private String address;
	private String email;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "owner_id", referencedColumnName = "id")
	private User owner;
	@OneToMany(mappedBy = "shop")
	private Set<Store> stores = new HashSet<>();
	@OneToMany(mappedBy = "shop")
	private Set<PointOfSale> pointOfSales = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<Store> getStores() {
		return stores;
	}

	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}

	public Set<PointOfSale> getPointOfSales() {
		return pointOfSales;
	}

	public void setPointOfSales(Set<PointOfSale> pointOfSales) {
		this.pointOfSales = pointOfSales;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void updateInternal(Shop shop) {
		this.name = shop.getName();
		this.address = shop.address;
		this.owner = shop.getOwner();
		this.website = shop.getWebsite();
		this.phone = shop.getPhone();
		this.email = shop.email;
		this.stores = shop.getStores();
		this.pointOfSales = shop.getPointOfSales();
	}

	public void addStore(Store store) {
		this.stores.add(store);
	}
}
