package cm.gasmyr.app.ims.core.shop;

import javax.persistence.Entity;
import javax.persistence.Table;

import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.util.Utils;

@Entity
@Table(name = "currency")
public class Currency extends AbstractDomainObject {
	private String code;
	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean canBeSave() {
		return Utils.isValid(code);
	}

	public void updateInternal(Currency currency) {
		this.code = currency.getCode();
		this.value = currency.getValue();
	}
}
