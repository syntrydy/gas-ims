package cm.gasmyr.app.ims.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cm.gasmyr.app.ims.core.sale.Sale;
import cm.gasmyr.app.ims.core.sale.SaleItem;
import cm.gasmyr.app.ims.service.sale.SaleItemService;
import cm.gasmyr.app.ims.service.sale.SaleService;
import cm.gasmyr.app.ims.service.stock.ItemMovementService;
import cm.gasmyr.app.ims.service.stock.MovementService;

@RestController
@RequestMapping(value = "/api")
public class SaleRestController {

	private static final Logger logger = LoggerFactory.getLogger(SaleRestController.class);

	final MovementService movementService;
	final ItemMovementService itemMovementService;
	final SaleService saleService;
	final SaleItemService saleItemService;

	@Autowired
	public SaleRestController(MovementService movementService, ItemMovementService itemMovementService,
			SaleService saleService, SaleItemService saleItemService) {
		this.movementService = movementService;
		this.itemMovementService = itemMovementService;
		this.saleService = saleService;
		this.saleItemService = saleItemService;
	}
	
	@RequestMapping(value = "/sale", method = RequestMethod.DELETE)
	public MyResponse deletePurchase(@RequestBody Sale sale) {
		boolean status = true;
		try {
			saleService.delete(sale.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/saleitem", method = RequestMethod.DELETE)
	public MyResponse deletePurchase(@RequestBody SaleItem saleItem) {
		boolean status = true;
		try {
			saleItem.setSale(null);
			saleItemService.delete(saleItem.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}
	
	@RequestMapping(value = "/saleitem", method = RequestMethod.POST)
	public MyResponse addPurchaseItem(@RequestBody SaleItem saleItem) {
		boolean status = true;
		try {
			if (saleItem.canBeSave()) {
				saleItemService.saveOrUpdate(saleItem);
			}
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

}
