package cm.gasmyr.app.ims.core.payment;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cm.gasmyr.app.ims.core.sale.Sale;

@Entity
@Table(name = "salepayment")
public class SalePayment extends AbstractPayment {
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "sale_id", referencedColumnName = "id")
	private Sale sale;


	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public void updateInternal(SalePayment payment) {
		super.updateInternal(this);
		this.sale = payment.getSale();
	}
}
