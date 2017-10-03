package cm.gasmyr.app.ims.core.sale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.core.stockmovement.ItemMovement;
import cm.gasmyr.app.ims.util.Utils;

@Entity
@Table(name = "saleitem")
public class SaleItem extends AbstractDomainObject {
	private int quantity;
	private double unitPrice;
	@Transient
	private double amount;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "itemmovement_id", referencedColumnName = "id")
	private ItemMovement itemMovement;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "sale_id", referencedColumnName = "id")
	private Sale sale;

	public double getAmount() {
		this.amount = this.quantity * this.unitPrice;
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public ItemMovement getItemMovement() {
		return itemMovement;
	}

	public void setItemMovement(ItemMovement itemMovement) {
		this.itemMovement = itemMovement;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		if (this.sale != null) {
			this.sale.internalRemoveSaleItem(this);
		}
		this.sale = sale;
		if (sale != null) {
			sale.internalAddSaleItem(this);
		}
	}

	public void updateInternal(SaleItem item) {
		this.amount = item.getAmount();
		this.itemMovement = item.getItemMovement();
		this.sale = item.getSale();
		this.unitPrice = item.getUnitPrice();
	}

	public boolean canBeSave() {
		return Utils.isValid(itemMovement) && Utils.isValid(sale) && Utils.isValid(quantity)
				&& Utils.isValid(unitPrice);
	}

}
