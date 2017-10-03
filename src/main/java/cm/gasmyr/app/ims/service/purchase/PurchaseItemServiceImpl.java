package cm.gasmyr.app.ims.service.purchase;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.purchase.PurchaseItem;
import cm.gasmyr.app.ims.repository.category.ItemRepository;
import cm.gasmyr.app.ims.repository.purchase.PurchaseItemRepository;
import cm.gasmyr.app.ims.repository.purchase.PurchaseRepository;
import cm.gasmyr.app.ims.repository.shop.UnitOfMeasureRepo;

@Service
@Component
public class PurchaseItemServiceImpl implements PurchaseItemService {

	final PurchaseItemRepository purchaseItemRepository;
	final PurchaseRepository purchaseRepository;
	final UnitOfMeasureRepo unitOfMeasureRepo;
	final ItemRepository itemRepository;

	@Autowired
	public PurchaseItemServiceImpl(PurchaseItemRepository purchaseItemRepository, PurchaseRepository purchaseRepository,
			UnitOfMeasureRepo unitOfMeasureRepo, ItemRepository itemRepository) {
		this.purchaseItemRepository = purchaseItemRepository;
		this.purchaseRepository = purchaseRepository;
		this.unitOfMeasureRepo = unitOfMeasureRepo;
		this.itemRepository = itemRepository;
	}

	@Override
	public List<PurchaseItem> listAll() {
		return purchaseItemRepository.findAll();
	}

	@Override
	public PurchaseItem getById(Long id) {
		return purchaseItemRepository.findOne(id);
	}

	@Override
	public PurchaseItem saveOrUpdate(PurchaseItem domainObject) {
		domainObject.setItem(itemRepository.findOne(domainObject.getItem().getId()));
		domainObject.setPurchase(purchaseRepository.findOne(domainObject.getPurchase().getId()));
		domainObject.setUnitOfMeasure(unitOfMeasureRepo.findOne(domainObject.getUnitOfMeasure().getId()));
		domainObject.setAvquantity(domainObject.getQuantity());
		PurchaseItem purchaseItem = domainObject;
		if (domainObject.getId() != null) {
			purchaseItem = purchaseItemRepository.findOne(domainObject.getId());
			if (purchaseItem != null) {
				purchaseItem.updateInternal(domainObject);
			}
		}
		return purchaseItemRepository.save(purchaseItem);
	}

	@Override
	public void delete(Long id) {
		purchaseItemRepository.delete(id);
	}

}
