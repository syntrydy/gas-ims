package cm.gasmyr.app.ims.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.gasmyr.app.ims.core.shop.Currency;
import cm.gasmyr.app.ims.service.shop.CurrencyService;
import cm.gasmyr.app.ims.util.Utils;

@Controller
public class CurrencyController {

	final CurrencyService currencyService;

	@Autowired
	public CurrencyController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	@RequestMapping(value = "/currencies", method = RequestMethod.GET)
	public String roles(Model model, Principal principal) {
		model.addAttribute("username", principal.getName().toUpperCase());
		model.addAttribute("currencies", currencyService.listAll());
		return "CurrencyListPage";
	}

	@RequestMapping(value = "/currency", method = RequestMethod.GET)
	public String role(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("currency", new Currency());
		return "CurrencyAddPage";
	}

	@RequestMapping(value = "/currency", method = RequestMethod.POST)
	public String save(Model model, @Valid Currency currency, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		if (currency.canBeSave()) {
			currencyService.saveOrUpdate(currency);
		}
		model.addAttribute("currencies", currencyService.listAll());
		return "redirect:/currencies";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/ecurrency/{id}")
	public String gotoeditItem(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("currency", currencyService.getById(id));
		return "CurrencyEditPage";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/ecurrency")
	public String editItem(@Valid Currency currency, Model model, Principal principal) {
		if (currency.canBeSave()) {
			currencyService.saveOrUpdate(currency);
		}
		model.addAttribute("currencies", currencyService.listAll());
		return "redirect:/currencies";
	}

}
