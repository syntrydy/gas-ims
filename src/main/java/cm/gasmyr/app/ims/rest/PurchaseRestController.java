package cm.gasmyr.app.ims.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cm.gasmyr.app.ims.core.purchase.Purchase;
import cm.gasmyr.app.ims.core.purchase.PurchaseItem;
import cm.gasmyr.app.ims.service.provider.ProviderService;
import cm.gasmyr.app.ims.service.purchase.PurchaseItemService;
import cm.gasmyr.app.ims.service.purchase.PurchaseService;
import cm.gasmyr.app.ims.service.store.StoreService;

@RestController
@RequestMapping(value = "/api")
public class PurchaseRestController {
	private static final Logger logger = LoggerFactory.getLogger(PurchaseRestController.class);
	final PurchaseService purchaseService;
	final PurchaseItemService purchaseItemService;
	final StoreService storeService;
	final ProviderService providerService;

	@Autowired
	public PurchaseRestController(PurchaseService purchaseService, PurchaseItemService purchaseItemService,
			StoreService storeService, ProviderService providerService) {
		this.purchaseService = purchaseService;
		this.purchaseItemService = purchaseItemService;
		this.storeService = storeService;
		this.providerService = providerService;
	}

	@RequestMapping(value = "/purchase", method = RequestMethod.DELETE)
	public MyResponse deletePurchase(@RequestBody Purchase purchase) {
		boolean status = true;
		try {
			purchaseService.delete(purchase.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/purchaseitem", method = RequestMethod.DELETE)
	public MyResponse deletePurchase(@RequestBody PurchaseItem purchaseItem) {
		boolean status = true;
		try {
			purchaseItem.setPurchase(null);
			purchaseItemService.delete(purchaseItem.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}
	
	@RequestMapping(value = "/purchaseitem", method = RequestMethod.POST)
	public MyResponse addPurchaseItem(@RequestBody PurchaseItem purchaseItem) {
		boolean status = true;
		try {
			if (purchaseItem.canBeSave()) {
				purchaseItemService.saveOrUpdate(purchaseItem);
			}
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}
	

}
