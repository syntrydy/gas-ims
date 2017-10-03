package cm.gasmyr.app.ims.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.gasmyr.app.ims.core.purchase.Purchase;
import cm.gasmyr.app.ims.service.category.ItemService;
import cm.gasmyr.app.ims.service.provider.ProviderService;
import cm.gasmyr.app.ims.service.purchase.PurchaseItemService;
import cm.gasmyr.app.ims.service.purchase.PurchaseService;
import cm.gasmyr.app.ims.service.store.StoreService;
import cm.gasmyr.app.ims.util.Utils;

@Controller
public class PurchaseController {

	final PurchaseService purchaseService;
	final PurchaseItemService purchaseItemService;
	final StoreService storeService;
	final ProviderService providerService;
	final ItemService itemService;

	@Autowired
	public PurchaseController(PurchaseService purchaseService, PurchaseItemService purchaseItemService,
			StoreService storeService, ProviderService providerService, ItemService itemService) {
		this.purchaseService = purchaseService;
		this.purchaseItemService = purchaseItemService;
		this.storeService = storeService;
		this.providerService = providerService;
		this.itemService = itemService;
	}

	@RequestMapping(value = "/purchases", method = RequestMethod.GET)
	public String purchases(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("purchases", purchaseService.listAll());
		return "PurchaseListPage";
	}

	@RequestMapping(value = "/purchase", method = RequestMethod.GET)
	public String purchase(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("purchase", new Purchase());
		model.addAttribute("providers", providerService.listAll());
		model.addAttribute("stores", storeService.listAll());
		return "PurchaseAddPage";
	}

	@RequestMapping(value = "/purchase", method = RequestMethod.POST)
	public String save(@Valid Purchase purchase, BindingResult bindingResult, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		if (purchase.canBeSave()) {
			purchase = purchaseService.saveOrUpdate(purchase);
		}
		model.addAttribute("purchase", purchase);
		return "redirect:/purchase/" + purchase.getId();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/purchase/{id}")
	public String gotodetail(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("purchase", purchaseService.getById(id));
		model.addAttribute("items", itemService.listAll());
		model.addAttribute("stores", storeService.listAll());
		return "PurchaseDetailPage";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/epurchase/{id}")
	public String gotoedit(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("purchase", purchaseService.getById(id));
		model.addAttribute("providers", providerService.listAll());
		model.addAttribute("stores", storeService.listAll());
		return "PurchaseEditPage";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/epurchase")
	public String edit(@Valid Purchase purchase, Model model, Principal principal) {
		if (purchase.canBeSave()) {
			purchaseService.saveOrUpdate(purchase);
		}
		model.addAttribute("purchases", purchaseService.listAll());
		return "redirect:/purchases";
	}
}
