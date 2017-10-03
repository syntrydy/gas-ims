package cm.gasmyr.app.ims.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.gasmyr.app.ims.core.sale.Sale;
import cm.gasmyr.app.ims.service.auth.UserService;
import cm.gasmyr.app.ims.service.customer.CustomerService;
import cm.gasmyr.app.ims.service.pointofsale.PointOfSaleService;
import cm.gasmyr.app.ims.service.sale.SaleItemService;
import cm.gasmyr.app.ims.service.sale.SaleService;
import cm.gasmyr.app.ims.service.stock.ItemMovementService;
import cm.gasmyr.app.ims.service.stock.MovementService;
import cm.gasmyr.app.ims.util.Utils;

@Controller
public class SaleController {

	final SaleService saleService;
	final SaleItemService saleItemService;
	final MovementService movementService;
	final ItemMovementService itemMovementService;
	final CustomerService customerService;
	final UserService userService;
	final PointOfSaleService pointOfSaleService;

	@Autowired
	public SaleController(SaleService saleService, SaleItemService saleItemService, MovementService movementService,
			ItemMovementService itemMovementService, CustomerService customerService, UserService userService,
			PointOfSaleService pointOfSaleService) {
		this.saleService = saleService;
		this.saleItemService = saleItemService;
		this.movementService = movementService;
		this.itemMovementService = itemMovementService;
		this.customerService = customerService;
		this.userService = userService;
		this.pointOfSaleService = pointOfSaleService;
	}

	@RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
	public String getInvoice(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("sale", saleService.getById(id));
		return "SaleInvoice";
	}

	@RequestMapping(value = "/sales", method = RequestMethod.GET)
	public String sales(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("sales", saleService.listAll());
		return "SaleListPage";
	}

	@RequestMapping(value = "/sale", method = RequestMethod.GET)
	public String purchase(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("sale", new Sale());
		model.addAttribute("customers", customerService.listAll());
		model.addAttribute("users", userService.listAll());
		model.addAttribute("pofsales", pointOfSaleService.listAll());
		return "SaleAddPage";
	}

	@RequestMapping(value = "/sale", method = RequestMethod.POST)
	public String save(Model model, @Valid Sale sale, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		if (sale.canBeSave()) {
			sale=saleService.saveOrUpdate(sale);
		}
		return "redirect:/sale/"+sale.getId();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/sale/{id}")
	public String gotodetail(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("sale", saleService.getById(id));
		return "SaleDetailPage";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/esale/{id}")
	public String gotoedit(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("sale", saleService.getById(id));
		return "SaleEditPage";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/esale")
	public String edit(@Valid Sale sale, Model model, Principal principal) {
		if (sale.canBeSave()) {
			saleService.saveOrUpdate(sale);
		}
		model.addAttribute("sales", saleService.listAll());
		return "redirect:/sales";
	}

}
