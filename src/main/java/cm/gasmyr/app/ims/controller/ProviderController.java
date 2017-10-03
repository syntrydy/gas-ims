package cm.gasmyr.app.ims.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.gasmyr.app.ims.core.provider.Provider;
import cm.gasmyr.app.ims.service.provider.ProviderService;
import cm.gasmyr.app.ims.util.Utils;

@Controller
public class ProviderController {

	final ProviderService providerService;

	@Autowired
	public ProviderController(ProviderService currencyService) {
		this.providerService = currencyService;
	}

	@RequestMapping(value = "/providers", method = RequestMethod.GET)
	public String roles(Model model, Principal principal) {
		model.addAttribute("username", principal.getName().toUpperCase());
		model.addAttribute("providers", providerService.listAll());
		return "ProviderListPage";
	}

	@RequestMapping(value = "/provider", method = RequestMethod.GET)
	public String role(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("provider", new Provider());
		return "ProviderAddPage";
	}

	@RequestMapping(value = "/provider", method = RequestMethod.POST)
	public String save(Model model, @Valid Provider provider, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		if (provider.canBeSave()) {
			providerService.saveOrUpdate(provider);
		}
		model.addAttribute("providers", providerService.listAll());
		return "redirect:/providers";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/provider/{id}")
	public String gotodetail(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("provider", providerService.getById(id));
		return "ProviderDetailPage";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/eprovider/{id}")
	public String gotoedit(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("provider", providerService.getById(id));
		return "ProviderEditPage";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/eprovider")
	public String edit(@Valid Provider provider, Model model, Principal principal) {
		if (provider.canBeSave()) {
			providerService.saveOrUpdate(provider);
		}
		model.addAttribute("providers", providerService.listAll());
		return "redirect:/providers";
	}

}
