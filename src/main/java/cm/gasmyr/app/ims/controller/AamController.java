package cm.gasmyr.app.ims.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.gasmyr.app.ims.util.Utils;

@Controller
public class AamController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		return "HomePage";
	}
	
	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public String config(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		return "ShopConfigPage";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		return "UserProfilePage";
	}
	
	

}
