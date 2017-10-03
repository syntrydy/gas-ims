package cm.gasmyr.app.ims.service.category;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.shop.UnitOfMeasure;
import cm.gasmyr.app.ims.repository.shop.UnitOfMeasureRepo;

@Service
@Component
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	final UnitOfMeasureRepo unitOfMeasureRepo;

	@Autowired
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepo unitOfMeasureRepo) {
		this.unitOfMeasureRepo = unitOfMeasureRepo;
	}

	@Override
	public List<UnitOfMeasure> listAll() {
		return (List<UnitOfMeasure>) unitOfMeasureRepo.findAll();
	}

	@Override
	public UnitOfMeasure getById(Long id) {
		return unitOfMeasureRepo.findOne(id);
	}

	@Override
	public UnitOfMeasure saveOrUpdate(UnitOfMeasure domainObject) {
		UnitOfMeasure unitOfMeasure = domainObject;
		if (domainObject.getId() != null) {
			unitOfMeasure = unitOfMeasureRepo.findOne(domainObject.getId());
			if (unitOfMeasure != null) {
				unitOfMeasure.updateInternal(domainObject);
			}
		}
		return unitOfMeasureRepo.save(unitOfMeasure);
	}

	@Override
	public void delete(Long id) {
		unitOfMeasureRepo.delete(id);
	}

}
