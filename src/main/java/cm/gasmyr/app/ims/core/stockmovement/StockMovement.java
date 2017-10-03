package cm.gasmyr.app.ims.core.stockmovement;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.core.pointofsale.PointOfSale;
import cm.gasmyr.app.ims.core.purchase.Purchase;
import cm.gasmyr.app.ims.core.store.Store;

@Entity
@Table(name = "stockmovement")
public class StockMovement extends AbstractDomainObject {
	private String date;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "store_id", referencedColumnName = "id")
	private Store store;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "pointofsale_id", referencedColumnName = "id")
	private PointOfSale pointOfSale;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "purchase_id", referencedColumnName = "id")
	private Purchase purchase;
	private boolean fromStore;
	@OneToMany(mappedBy = "stockMovement", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<ItemMovement> itemMovements = new HashSet<>();

	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isFromStore() {
		return fromStore;
	}

	public void setFromStore(boolean fromStore) {
		this.fromStore = fromStore;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public PointOfSale getPointOfSale() {
		return pointOfSale;
	}

	public void setPointOfSale(PointOfSale pointOfSale) {
		this.pointOfSale = pointOfSale;
	}

	public Set<ItemMovement> getItemMovements() {
		return itemMovements;
	}

	public void setItemMovements(Set<ItemMovement> itemMovements) {
		this.itemMovements = itemMovements;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public void updateInternal(StockMovement movement) {
		this.date = movement.getDate();
		this.fromStore = movement.isFromStore();
		this.store = movement.getStore();
		this.pointOfSale = movement.getPointOfSale();
	}

	public boolean canBeSave() {
		return this.date != null && this.store != null && this.pointOfSale != null && purchase != null;
	}
}
