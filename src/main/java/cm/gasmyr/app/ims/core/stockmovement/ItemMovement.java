package cm.gasmyr.app.ims.core.stockmovement;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.core.purchase.PurchaseItem;
import cm.gasmyr.app.ims.util.Utils;

@Entity
@Table(name = "itemmovement")
public class ItemMovement extends AbstractDomainObject {

	private int quantity;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "purchaseitem_id", referencedColumnName = "id")
	private PurchaseItem purchaseItem;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "stockmovement_id", referencedColumnName = "id")
	private StockMovement stockMovement;

	public PurchaseItem getPurchaseItem() {
		return purchaseItem;
	}

	public void setPurchaseItem(PurchaseItem purchaseItem) {
		this.purchaseItem = purchaseItem;
	}

	public StockMovement getStockMovement() {
		return stockMovement;
	}

	public void setStockMovement(StockMovement stockMovement) {
		this.stockMovement = stockMovement;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void updateInternal(ItemMovement movement) {

	}

	public boolean canBeSave() {
		return Utils.isValid(purchaseItem) && Utils.isValid(stockMovement) && Utils.isValid(quantity);
	}
}
