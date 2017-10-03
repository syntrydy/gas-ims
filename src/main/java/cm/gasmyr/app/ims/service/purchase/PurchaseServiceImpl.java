package cm.gasmyr.app.ims.service.purchase;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.purchase.Purchase;
import cm.gasmyr.app.ims.repository.purchase.PurchaseRepository;

@Service
@Component
public class PurchaseServiceImpl implements PurchaseService {

	final PurchaseRepository purchaseRepository;

	@Autowired
	public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
		super();
		this.purchaseRepository = purchaseRepository;
	}

	@Override
	public List<Purchase> listAll() {
		return purchaseRepository.findAll();
	}

	@Override
	public Purchase getById(Long id) {
		return purchaseRepository.findOne(id);
	}

	@Override
	public Purchase saveOrUpdate(Purchase domainObject) {
		Purchase purchase = domainObject;
		if (domainObject.getId() != null) {
			purchase = purchaseRepository.findOne(domainObject.getId());
			if (purchase != null) {
				purchase.updateInternal(domainObject);
			}
		}
		return purchaseRepository.save(purchase);
	}

	@Override
	public void delete(Long id) {
		purchaseRepository.delete(id);
	}

}
