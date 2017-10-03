package cm.gasmyr.app.ims.core.notification;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.core.category.Item;

@Entity
@Table(name = "itemnotification")
public class ItemNotification extends AbstractDomainObject {
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "item_id", referencedColumnName = "id")
	private Item item;
	private Date date;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void updateInternal(ItemNotification notification) {
		this.date = notification.getDate();
		this.item = notification.getItem();
	}
}
