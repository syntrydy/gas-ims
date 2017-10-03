package cm.gasmyr.app.ims.service;

import java.util.List;

import cm.gasmyr.app.ims.core.AbstractDomainObject;

public interface CrudService<T extends AbstractDomainObject> {
	
	List<T> listAll();

	T getById(Long id);

	T saveOrUpdate(T domainObject);

	void delete(Long id);
}
