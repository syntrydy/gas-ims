package cm.gasmyr.app.ims.core.shop;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.core.category.Item;
import cm.gasmyr.app.ims.util.Utils;

@Entity
@Table(name = "unitofmeasure")
public class UnitOfMeasure extends AbstractDomainObject {
	private String name;
	private int quantity;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private Item item;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void updateInternal(UnitOfMeasure unitOfMeasure) {
		this.name = unitOfMeasure.getName();
		this.quantity = unitOfMeasure.getQuantity();
		this.item = unitOfMeasure.getItem();
	}

	public boolean canBeSave() {
		return Utils.isValid(getName()) && getItem() != null;
	}

}
