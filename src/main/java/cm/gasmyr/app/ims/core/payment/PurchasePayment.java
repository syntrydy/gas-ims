package cm.gasmyr.app.ims.core.payment;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cm.gasmyr.app.ims.core.purchase.Purchase;

@Entity
@Table(name = "purchasepayment")
public class PurchasePayment extends AbstractPayment {
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "purchase_id", referencedColumnName = "id")
	private Purchase purchase;

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public void updateInternal(PurchasePayment payment) {
		super.updateInternal(this);
		this.purchase = payment.getPurchase();
	}
}
