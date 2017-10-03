package cm.gasmyr.app.ims.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cm.gasmyr.app.ims.core.stockmovement.ItemMovement;
import cm.gasmyr.app.ims.core.stockmovement.StockMovement;
import cm.gasmyr.app.ims.service.purchase.PurchaseItemService;
import cm.gasmyr.app.ims.service.purchase.PurchaseService;
import cm.gasmyr.app.ims.service.stock.ItemMovementService;
import cm.gasmyr.app.ims.service.stock.MovementService;
import cm.gasmyr.app.ims.service.store.StoreService;

@RestController
@RequestMapping(value = "/api")
public class MovementRestController {
	private static final Logger logger = LoggerFactory.getLogger(MovementRestController.class);
	final PurchaseService purchaseService;
	final PurchaseItemService purchaseItemService;
	final StoreService storeService;
	final MovementService movementService;
	final ItemMovementService itemMovementService;

	public MovementRestController(PurchaseService purchaseService, PurchaseItemService purchaseItemService,
			StoreService storeService, MovementService movementService, ItemMovementService itemMovementService) {
		this.purchaseService = purchaseService;
		this.purchaseItemService = purchaseItemService;
		this.storeService = storeService;
		this.movementService = movementService;
		this.itemMovementService = itemMovementService;
	}

	@RequestMapping(value = "/movement", method = RequestMethod.DELETE)
	public MyResponse deleteMovement(@RequestBody StockMovement movement) {
		boolean status = true;
		try {
			movementService.delete(movement.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/itemmovement", method = RequestMethod.DELETE)
	public MyResponse deleteMovement(@RequestBody ItemMovement movement) {
		boolean status = true;
		try {
			itemMovementService.delete(movement.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/itemmovement", method = RequestMethod.POST)
	public MyResponse addPurchaseItem(@RequestBody ItemMovement movement) {
		boolean status = true;
		String message = "";
		try {
			if (movement.canBeSave()) {
				itemMovementService.saveOrUpdate(movement);
			}
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
			message += e.getMessage();
		}
		return new MyResponse(status, message.toString());
	}

}
