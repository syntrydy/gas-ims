package cm.gasmyr.app.ims.core.purchase;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.core.provider.Provider;
import cm.gasmyr.app.ims.core.store.Store;
import cm.gasmyr.app.ims.util.Utils;

@Entity
@Table(name = "purchase")
public class Purchase extends AbstractDomainObject {
	private String date;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "provider_id", referencedColumnName = "id")
	private Provider provider;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "store_id", referencedColumnName = "id")
	private Store store;
	@Transient
	private double amount;
	@OneToMany(mappedBy = "purchase", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<PurchaseItem> purchaseItems = new HashSet<>();

	public double getAmount() {
		return purchaseItems.stream().mapToDouble(p -> p.getPrice()).sum();
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Set<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}

	public void setPurchaseItems(Set<PurchaseItem> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}

	public void updateInternal(Purchase purchase) {
		this.date = purchase.getDate();
		this.store = purchase.getStore();
		this.provider = purchase.getProvider();
	}

	public boolean canBeSave() {
		return Utils.isValid(date) && Utils.isValid(provider) && Utils.isValid(store);
	}
}
