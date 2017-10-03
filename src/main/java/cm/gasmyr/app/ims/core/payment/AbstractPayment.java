package cm.gasmyr.app.ims.core.payment;

import javax.persistence.MappedSuperclass;

import cm.gasmyr.app.ims.core.AbstractDomainObject;

@MappedSuperclass
public class AbstractPayment extends AbstractDomainObject{
	private String date;
	private double amount;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void updateInternal(AbstractPayment payment) {
		this.date = payment.getDate();
		this.amount = payment.getAmount();
	}
}
