package cm.gasmyr.app.ims.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.gasmyr.app.ims.core.auth.Role;
import cm.gasmyr.app.ims.service.auth.RoleService;
import cm.gasmyr.app.ims.util.Utils;

@Controller
public class RoleController {

	RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public String roles(Model model, Principal principal) {
		model.addAttribute("username", principal.getName().toUpperCase());
		model.addAttribute("roles", roleService.listAll());
		return "RoleListPage";
	}

	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public String role(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("role", new Role());
		return "RoleAddPage";
	}

	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public String save(Model model, @Valid Role role, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		if (role.canBeSave()) {
			roleService.saveOrUpdate(role);
		}
		model.addAttribute("roles", roleService.listAll());
		return "redirect:/roles";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/erole/{id}")
	public String gotoedit(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("role", roleService.getById(id));
		return "RoleEditPage";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/erole")
	public String edit(@Valid Role role, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		if (role.canBeSave()) {
			roleService.saveOrUpdate(role);
		}
		model.addAttribute("roles", roleService.listAll());
		return "redirect:/roles";
	}

}
