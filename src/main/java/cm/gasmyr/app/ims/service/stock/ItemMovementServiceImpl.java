package cm.gasmyr.app.ims.service.stock;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.purchase.PurchaseItem;
import cm.gasmyr.app.ims.core.stockmovement.ItemMovement;
import cm.gasmyr.app.ims.repository.purchase.PurchaseItemRepository;
import cm.gasmyr.app.ims.repository.purchase.PurchaseRepository;
import cm.gasmyr.app.ims.repository.stock.ItemMovementRepository;
import cm.gasmyr.app.ims.repository.stock.MovementRepository;

@Service
@Component
public class ItemMovementServiceImpl implements ItemMovementService {

	final ItemMovementRepository itemMovementRepository;
	final PurchaseItemRepository purchaseItemRepository;
	final MovementRepository movementRepository;
	final PurchaseRepository purchaseRepository;

	@Autowired
	public ItemMovementServiceImpl(ItemMovementRepository itemMovementRepository,
			PurchaseItemRepository purchaseItemRepository, MovementRepository movementRepository,
			PurchaseRepository purchaseRepository) {
		this.itemMovementRepository = itemMovementRepository;
		this.purchaseItemRepository = purchaseItemRepository;
		this.movementRepository = movementRepository;
		this.purchaseRepository = purchaseRepository;
	}

	@Override
	public List<ItemMovement> listAll() {
		return (List<ItemMovement>) itemMovementRepository.findAll();
	}

	@Override
	public ItemMovement getById(Long id) {
		return itemMovementRepository.findOne(id);
	}

	@Override
	public ItemMovement saveOrUpdate(ItemMovement itemMovement) {
		Long purchaseItemId = itemMovement.getPurchaseItem().getId();
		PurchaseItem item=purchaseItemRepository.getOne(purchaseItemId);
		item.setAvquantity(item.getAvquantity() - itemMovement.getQuantity());
		ItemMovement movement = itemMovement;
		movement.setPurchaseItem(item);
		movement.setStockMovement(movementRepository.getOne(movement.getStockMovement().getId()));
		if (itemMovement.getId() != null) {
			movement = itemMovementRepository.findOne(itemMovement.getId());
			if (movement != null) {
				movement.updateInternal(itemMovement);
			}
		}
		purchaseItemRepository.save(item);
		return itemMovementRepository.save(movement);
	}

	@Override
	public void delete(Long id) {
		ItemMovement movement = getById(id);
		movement.setPurchaseItem(purchaseItemRepository.getOne(movement.getPurchaseItem().getId()));
		PurchaseItem item = movement.getPurchaseItem();
		item.setAvquantity(item.getAvquantity() + movement.getQuantity());
		purchaseItemRepository.save(item);
		itemMovementRepository.delete(id);
	}

}
