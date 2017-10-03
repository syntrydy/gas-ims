package cm.gasmyr.app.ims.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.gasmyr.app.ims.core.auth.User;
import cm.gasmyr.app.ims.service.EncryptionService;
import cm.gasmyr.app.ims.service.auth.RoleService;
import cm.gasmyr.app.ims.service.auth.UserService;
import cm.gasmyr.app.ims.ui.domain.UiUser;
import cm.gasmyr.app.ims.util.Utils;

@Controller
public class UserController {

	final UserService userService;
	final RoleService roleService;
	final EncryptionService encryptionService;

	@Autowired
	public UserController(UserService userService, EncryptionService encryptionService, RoleService roleService) {
		this.userService = userService;
		this.encryptionService = encryptionService;
		this.roleService = roleService;
	}

	@RequestMapping(value = "/master", method = RequestMethod.GET)
	public String master(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		return "master";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String users(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("users", userService.listAll());
		return "UserListPage";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("roles", roleService.listAll());
		model.addAttribute("user", new User());
		return "UserAddPage";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String save(Model model, @Valid User user, Principal principal) {
		user.setUsername(user.getEmail());
		user.setActive(true);
		user.setEncryptedpassword(encryptionService.encryptString(user.getPassword()));
		model.addAttribute("username", Utils.getUserName(principal));
		if (user.canBeSave()) {
			userService.saveOrUpdate(user);
		}
		model.addAttribute("users", userService.listAll());
		return "redirect:/users";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new UiUser());
		model.addAttribute("error", false);
		return "LoginPage";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
	public String gotodetail(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("user", userService.getById(id));
		return "UserDetailPage";
	}
	@RequestMapping(method = RequestMethod.GET, value = "/euser/{id}")
	public String gotoedit(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("roles", roleService.listAll());
		model.addAttribute("user", userService.getById(id));
		return "UserEditPage";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/euser")
	public String edit(@Valid User user, Model model, Principal principal) {
		user.setUsername(user.getEmail());
		user.setActive(true);
		user.setEncryptedpassword(encryptionService.encryptString(user.getPassword()));
		model.addAttribute("username", Utils.getUserName(principal));
		if (user.canBeSave()) {
			userService.saveOrUpdate(user);
		}
		model.addAttribute("users", userService.listAll());
		return "redirect:/users";
	}
}
