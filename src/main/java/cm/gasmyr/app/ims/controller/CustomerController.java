package cm.gasmyr.app.ims.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.gasmyr.app.ims.core.customer.Customer;
import cm.gasmyr.app.ims.service.customer.CustomerService;
import cm.gasmyr.app.ims.util.Utils;

@Controller
public class CustomerController {

	final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String roles(Model model, Principal principal) {
		model.addAttribute("username", principal.getName().toUpperCase());
		model.addAttribute("customers", customerService.listAll());
		return "CustomerListPage";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String role(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("customer", new Customer());
		return "CustomerAddPage";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public String save(Model model, @Valid Customer customer, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		if (customer.canBeSave()) {
			customerService.saveOrUpdate(customer);
		}
		model.addAttribute("customers", customerService.listAll());
		return "redirect:/customers";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/customer/{id}")
	public String gotodetailItem(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("customer", customerService.getById(id));
		return "CustomerDetailPage";
	}
	@RequestMapping(method = RequestMethod.GET, value = "/ecustomer/{id}")
	public String gotoeditItem(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("customer", customerService.getById(id));
		return "CustomerEditPage";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/ecustomer")
	public String editItem(@Valid Customer customer, Model model, Principal principal) {
		if (customer.canBeSave()) {
			customerService.saveOrUpdate(customer);
		}
		model.addAttribute("customers", customerService.listAll());
		return "redirect:/customers";
	}

}
