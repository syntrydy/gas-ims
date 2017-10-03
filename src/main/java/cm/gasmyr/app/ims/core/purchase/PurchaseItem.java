package cm.gasmyr.app.ims.core.purchase;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.core.category.Item;
import cm.gasmyr.app.ims.core.shop.UnitOfMeasure;
import cm.gasmyr.app.ims.util.Utils;

@Entity
@Table(name = "purchaseitem")
public class PurchaseItem extends AbstractDomainObject {
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private Item item;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "measure_id", referencedColumnName = "id")
	private UnitOfMeasure unitOfMeasure;
	private int quantity;
	private int avquantity;
	@Transient
	private double unitPrice;
	private double price;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "purchase_id", referencedColumnName = "id")
	private Purchase purchase;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAvquantity() {
		return avquantity;
	}

	public void setAvquantity(int avquantity) {
		this.avquantity = avquantity;
	}

	public double getUnitPrice() {
		BigDecimal unit = new BigDecimal(this.price / quantity);
		unit = unit.setScale(4, RoundingMode.HALF_UP);
		return unit.doubleValue();
	}

	public void setUnitPrice() {
		this.unitPrice = this.getUnitPrice();
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void updateInternal(PurchaseItem purchaseItem) {
		this.item = purchaseItem.getItem();
		this.purchase = purchaseItem.getPurchase();
		this.price = purchaseItem.getPrice();
		this.unitOfMeasure = purchaseItem.getUnitOfMeasure();
		this.quantity = purchaseItem.getQuantity();
	}

	public boolean canBeSave() {
		return Utils.isValid(item) && Utils.isValid(purchase) && Utils.isValid(unitOfMeasure);
	}

}
