package cm.gasmyr.app.ims.core.category;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cm.gasmyr.app.ims.core.AbstractDomainObject;
import cm.gasmyr.app.ims.util.Utils;

@Entity
@Table(name = "category")
public class Category extends AbstractDomainObject {
	private String name;
	private String description;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	private Category parent;
	@OneToMany(mappedBy = "category", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Item> items = new HashSet<>();

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

	public Category getParent() {
		return parent;
	}

	public String getParentName() {
		if (parent != null) {
			return parent.name;
		} else {
			return "--";
		}
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public void remove(Item item) {
		this.items.remove(item);
	}

	public void updateInternal(Category category) {
		this.name = category.getName();
		this.description = category.getDescription();
		this.items = category.getItems();
		this.parent = category.getParent();
	}

	public boolean canBeSave() {
		return Utils.isValid(name);
	}
}
