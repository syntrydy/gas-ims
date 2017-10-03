package cm.gasmyr.app.ims.core.pointofsale;

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

import cm.gasmyr.app.ims.common.SaleStrategy;
import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.core.auth.User;
import cm.gasmyr.app.ims.core.customer.Customer;
import cm.gasmyr.app.ims.core.sale.Sale;
import cm.gasmyr.app.ims.core.shop.Currency;
import cm.gasmyr.app.ims.core.shop.Shop;
import cm.gasmyr.app.ims.util.Utils;

@Entity
@Table(name = "pointofsale")
public class PointOfSale extends AbstractDomainObject {
	private String name;
	private String address;
	private String phone;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "manager_id", referencedColumnName = "id")
	private User manager;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "currency_id", referencedColumnName = "id")
	private Currency currency;
	@Enumerated(EnumType.STRING)
	@Column(name = "salestrategy")
	private SaleStrategy saleStrategy = SaleStrategy.FIFO;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "defaultcustomer_id", referencedColumnName = "id")
	private Customer customer;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "shop_id", referencedColumnName = "id")
	private Shop shop;
	@OneToMany(mappedBy = "pointOfSale", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Sale> sales = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public SaleStrategy getSaleStrategy() {
		return saleStrategy;
	}

	public void setSaleStrategy(SaleStrategy saleStrategy) {
		this.saleStrategy = saleStrategy;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Set<Sale> getSales() {
		return sales;
	}

	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public void updateInternal(PointOfSale pointOfSale) {
		this.name = pointOfSale.getName();
		this.customer = pointOfSale.getCustomer();
		this.currency = pointOfSale.getCurrency();
		this.saleStrategy = pointOfSale.getSaleStrategy();
		this.address = pointOfSale.getAddress();
		this.phone = pointOfSale.getPhone();
		this.manager = pointOfSale.getManager();
	}

	public boolean canBeSave() {
		return Utils.isValid(this.name) && this.shop != null;
	}
}
