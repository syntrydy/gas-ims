package cm.gasmyr.app.ims.core.category;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.core.shop.UnitOfMeasure;
import cm.gasmyr.app.ims.util.Utils;

@Entity
@Table(name = "item")
public class Item extends AbstractDomainObject {
	private String name;
	private String description;
	private int threshold;
	@OneToMany(mappedBy = "item", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<UnitOfMeasure> getUnitOfMeasures() {
		return unitOfMeasures;
	}

	public void setUnitOfMeasures(Set<UnitOfMeasure> unitOfMeasures) {
		this.unitOfMeasures = unitOfMeasures;
	}

	public void updateInternal(Item item) {
		this.name = item.getName();
		this.description = item.getDescription();
		this.threshold = item.getThreshold();
		this.category = item.getCategory();
	}

	public boolean canBeSave() {
		return Utils.isValid(this.name) && this.category != null;
	}
}
