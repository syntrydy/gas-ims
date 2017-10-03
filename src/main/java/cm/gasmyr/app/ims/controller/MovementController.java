package cm.gasmyr.app.ims.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.gasmyr.app.ims.core.stockmovement.StockMovement;
import cm.gasmyr.app.ims.service.pointofsale.PointOfSaleService;
import cm.gasmyr.app.ims.service.purchase.PurchaseService;
import cm.gasmyr.app.ims.service.stock.ItemMovementService;
import cm.gasmyr.app.ims.service.stock.MovementService;
import cm.gasmyr.app.ims.service.store.StoreService;
import cm.gasmyr.app.ims.util.Utils;

@Controller
public class MovementController {

	final MovementService movementService;
	final ItemMovementService itemMovementService;
	final PurchaseService purchaseService;
	final StoreService storeService;
	final PointOfSaleService pointOfSaleService;

	public MovementController(MovementService movementService,
			cm.gasmyr.app.ims.service.stock.ItemMovementService itemMovementService, PurchaseService purchaseService,
			StoreService storeService, PointOfSaleService pointOfSaleService) {
		this.movementService = movementService;
		this.itemMovementService = itemMovementService;
		this.purchaseService = purchaseService;
		this.storeService = storeService;
		this.pointOfSaleService = pointOfSaleService;
	}

	@RequestMapping(value = "/movements", method = RequestMethod.GET)
	public String roles(Model model, Principal principal) {
		model.addAttribute("username", principal.getName().toUpperCase());
		model.addAttribute("movements", movementService.listAll());
		return "StockMovements";
	}

	@RequestMapping(value = "/movement", method = RequestMethod.GET)
	public String role(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("movement", new StockMovement());
		model.addAttribute("stores", storeService.listAll());
		model.addAttribute("pofsales", pointOfSaleService.listAll());
		model.addAttribute("purchases", purchaseService.listAll());
		return "StockMovementAddPage";
	}

	@RequestMapping(value = "/movement", method = RequestMethod.POST)
	public String save(Model model, @Valid StockMovement movement, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		if (movement.canBeSave()) {
			movement=movementService.saveOrUpdate(movement);
		}
		model.addAttribute("movements", movementService.listAll());
		return "redirect:/movement/"+movement.getId();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/movement/{id}")
	public String gotodetail(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("movement", movementService.getById(id));
		return "StockMovementDetailPage";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/emovement/{id}")
	public String gotoedit(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("movement", movementService.getById(id));
		model.addAttribute("stores", storeService.listAll());
		model.addAttribute("pofsales", pointOfSaleService.listAll());
		model.addAttribute("purchases", purchaseService.listAll());
		return "StockMovementEditPage";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/emovement")
	public String edit(@Valid StockMovement movement, Model model, Principal principal) {
		if (movement.canBeSave()) {
			movementService.saveOrUpdate(movement);
		}
		model.addAttribute("movements", movementService.listAll());
		return "redirect:/movements";
	}

}
