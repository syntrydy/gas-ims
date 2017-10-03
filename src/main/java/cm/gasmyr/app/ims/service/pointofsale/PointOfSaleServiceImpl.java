package cm.gasmyr.app.ims.service.pointofsale;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.pointofsale.PointOfSale;
import cm.gasmyr.app.ims.repository.pointofsale.PointOfSaleRepository;

@Service
@Component
public class PointOfSaleServiceImpl implements PointOfSaleService {

	final PointOfSaleRepository pointOfSaleRepository;

	@Autowired
	public PointOfSaleServiceImpl(PointOfSaleRepository pointOfSaleRepository) {
		this.pointOfSaleRepository = pointOfSaleRepository;
	}

	@Override
	public List<PointOfSale> listAll() {
		return (List<PointOfSale>) pointOfSaleRepository.findAll();
	}

	@Override
	public PointOfSale getById(Long id) {
		return pointOfSaleRepository.findOne(id);
	}

	@Override
	public PointOfSale saveOrUpdate(PointOfSale domainObject) {
		PointOfSale pointOfSale = domainObject;
		if (domainObject.getId() != null) {
			pointOfSale = pointOfSaleRepository.findOne(domainObject.getId());
			if (pointOfSale != null) {
				pointOfSale.updateInternal(domainObject);
			}
		}
		return pointOfSaleRepository.save(pointOfSale);
	}

	@Override
	public void delete(Long id) {
		pointOfSaleRepository.delete(id);
	}

}
