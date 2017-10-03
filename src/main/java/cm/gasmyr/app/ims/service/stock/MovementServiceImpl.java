package cm.gasmyr.app.ims.service.stock;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.stockmovement.StockMovement;
import cm.gasmyr.app.ims.repository.stock.MovementRepository;

@Service
@Component
public class MovementServiceImpl implements MovementService {

	final MovementRepository movementRepository;

	@Autowired
	public MovementServiceImpl(MovementRepository movementRepository) {
		super();
		this.movementRepository = movementRepository;
	}

	@Override
	public List<StockMovement> listAll() {
		return movementRepository.findAll();
	}

	@Override
	public StockMovement getById(Long id) {
		return movementRepository.findOne(id);
	}

	@Override
	public StockMovement saveOrUpdate(StockMovement domainObject) {
		StockMovement movement = domainObject;
		if (domainObject.getId() != null) {
			movement = movementRepository.findOne(domainObject.getId());
			if (movement != null) {
				movement.updateInternal(domainObject);
			}
		}
		return movementRepository.save(movement);
	}

	@Override
	public void delete(Long id) {
		movementRepository.delete(id);
	}

}
