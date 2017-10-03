package cm.gasmyr.app.ims.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.gasmyr.app.ims.core.store.Store;
import cm.gasmyr.app.ims.service.auth.UserService;
import cm.gasmyr.app.ims.service.shop.ShopService;
import cm.gasmyr.app.ims.service.store.StoreService;
import cm.gasmyr.app.ims.util.Utils;
@Controller
public class StoreController {
	final StoreService storeService;
	final ShopService shopService;
	final UserService userService;

	@Autowired
	public StoreController(StoreService storeService, ShopService shopService, UserService userService) {
		super();
		this.storeService = storeService;
		this.shopService = shopService;
		this.userService = userService;
	}

	@RequestMapping(value = "/stores", method = RequestMethod.GET)
	public String users(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("stores", storeService.listAll());
		return "StoreListPage";
	}

	@RequestMapping(value = "/store", method = RequestMethod.GET)
	public String user(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("store", new Store());
		model.addAttribute("users", userService.listAll());
		model.addAttribute("shops", shopService.listAll());
		return "StoreAddPage";
	}
	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public String save(Model model, @Valid Store store, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		if (store.canBeSave()) {
			storeService.saveOrUpdate(store);
		}
		model.addAttribute("stores", storeService.listAll());
		return "redirect:/stores";
	}
	@RequestMapping(method = RequestMethod.GET, value = "/estore/{id}")
	public String gotoeditItem(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("store", storeService.getById(id));
		model.addAttribute("users", userService.listAll());
		model.addAttribute("shops", shopService.listAll());
		return "StoreEditPage";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/estore")
	public String editItem(@Valid Store store, Model model, Principal principal) {
		if (store.canBeSave()) {
			storeService.saveOrUpdate(store);
		}
		model.addAttribute("stores", storeService.listAll());
		return "redirect:/stores";
	}

}
