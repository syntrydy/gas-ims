package cm.gasmyr.app.ims.core.sale;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cm.gasmyr.app.ims.common.PaymentStatus;
import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.core.auth.User;
import cm.gasmyr.app.ims.core.customer.Customer;
import cm.gasmyr.app.ims.core.payment.SalePayment;
import cm.gasmyr.app.ims.core.pointofsale.PointOfSale;
import cm.gasmyr.app.ims.util.Utils;

@Entity
@Table(name = "sale")
public class Sale extends AbstractDomainObject {
	private String date;
	@Transient
	private double amount;
	@Column(name = "payamount")
	private double payAmount;
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus = PaymentStatus.NOT_PAID;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "seller_id", referencedColumnName = "id")
	private User seller;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "pointofsale_id", referencedColumnName = "id")
	private PointOfSale pointOfSale;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	@OneToMany(mappedBy = "sale", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<SaleItem> items = new HashSet<>();
	@OneToMany(mappedBy = "sale", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<SalePayment> payments = new HashSet<>();

	@Transient
	private String invoiceNumber;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getAmount() {
		return items.stream().mapToDouble(si -> si.getAmount()).sum();
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Set<SaleItem> getItems() {
		return Collections.unmodifiableSet(items);
	}

	public void setItems(Set<SaleItem> items) {
		this.items = items;
	}

	public Set<SalePayment> getPayments() {
		return payments;
	}

	public void setPayments(Set<SalePayment> payments) {
		this.payments = payments;
	}

	public PaymentStatus getPaymentStatus() {
		if (getAmount() == getPayAmount() && getAmount() != 0) {
			return PaymentStatus.PAID;
		} else if (getAmount() > getPayAmount()) {
			return PaymentStatus.PARTIALLY_PAID;
		} else {
			return PaymentStatus.NOT_PAID;
		}
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public void addSaleItem(SaleItem saleItem) {
		saleItem.setSale(this);
	}

	public double getPayAmount() {
		return payments.stream().mapToDouble(p -> p.getAmount()).sum();
	}

	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}

	public PointOfSale getPointOfSale() {
		return pointOfSale;
	}

	public void setPointOfSale(PointOfSale pointOfSale) {
		this.pointOfSale = pointOfSale;
	}

	public void removeSaleItem(SaleItem saleItem) {
		saleItem.setSale(null);
	}

	public void internalAddSaleItem(SaleItem saleItem) {
		items.add(saleItem);
	}

	public void internalRemoveSaleItem(SaleItem saleItem) {
		items.remove(saleItem);
	}

	public String getInvoiceNumber() {
		return date.substring(10, 19);
	}

	public void updateInternal(Sale sale) {
		this.date = sale.getDate();
		this.customer = sale.getCustomer();
		this.seller = sale.getSeller();
		this.paymentStatus = sale.getPaymentStatus();
	}

	public boolean canBeSave() {
		return Utils.isValid(pointOfSale) && Utils.isValid(customer) && Utils.isValid(date) && Utils.isValid(seller);
	}

}
