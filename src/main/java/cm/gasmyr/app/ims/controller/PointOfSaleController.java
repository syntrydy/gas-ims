package cm.gasmyr.app.ims.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.gasmyr.app.ims.common.SaleStrategy;
import cm.gasmyr.app.ims.core.pointofsale.PointOfSale;
import cm.gasmyr.app.ims.service.auth.UserService;
import cm.gasmyr.app.ims.service.customer.CustomerService;
import cm.gasmyr.app.ims.service.pointofsale.PointOfSaleService;
import cm.gasmyr.app.ims.service.shop.CurrencyService;
import cm.gasmyr.app.ims.service.shop.ShopService;
import cm.gasmyr.app.ims.util.Utils;

@Controller
public class PointOfSaleController {

	final PointOfSaleService pointOfSaleService;
	final ShopService shopService;
	final CurrencyService currencyService;
	final UserService userService;
	final CustomerService customerService;

	@Autowired
	public PointOfSaleController(PointOfSaleService pointOfSaleService, ShopService shopService,
			CurrencyService currencyService, UserService userService, CustomerService customerService) {
		this.pointOfSaleService = pointOfSaleService;
		this.shopService = shopService;
		this.currencyService = currencyService;
		this.userService = userService;
		this.customerService = customerService;
	}

	@RequestMapping(value = "/pofsales", method = RequestMethod.GET)
	public String users(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("pofsales", pointOfSaleService.listAll());
		return "POfSaleListPage";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/pofsale/{id}")
	public String gotodetail(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("pofsale", pointOfSaleService.getById(id));
		return "POfSaleDetailPage";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/pofsale")
	public String addPage(Model model, Principal principal) {
		model.addAttribute("pofsale", new PointOfSale());
		model.addAttribute("users", userService.listAll());
		model.addAttribute("currencies", currencyService.listAll());
		model.addAttribute("shops", shopService.listAll());
		model.addAttribute("customers", customerService.listAll());
		List<SaleStrategy> strategies = Stream.of(SaleStrategy.values()).collect(Collectors.toList());
		model.addAttribute("strategies", strategies);
		return "POfSaleAddPage";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/pofsale")
	public String save(@Valid PointOfSale pointOfSale, Model model, Principal principal) {
		if (pointOfSale.canBeSave()) {
			pointOfSaleService.saveOrUpdate(pointOfSale);
		}
		model.addAttribute("pofsales", pointOfSaleService.listAll());
		return "redirect:/pofsales";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/epofsale/{id}")
	public String gotoedit(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("pofsale", pointOfSaleService.getById(id));
		model.addAttribute("users", userService.listAll());
		model.addAttribute("currencies", currencyService.listAll());
		model.addAttribute("shops", shopService.listAll());
		model.addAttribute("customers", customerService.listAll());
		List<SaleStrategy> strategies = Stream.of(SaleStrategy.values()).collect(Collectors.toList());
		model.addAttribute("strategies", strategies);
		return "POfSaleEditPage";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/epofsale")
	public String edit(@Valid PointOfSale pointOfSale, Model model, Principal principal) {
		if (pointOfSale.canBeSave()) {
			pointOfSaleService.saveOrUpdate(pointOfSale);
		}
		model.addAttribute("pofsales", pointOfSaleService.listAll());
		return "redirect:/pofsales";
	}

}
