package cm.gasmyr.app.ims.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cm.gasmyr.app.ims.core.auth.Role;
import cm.gasmyr.app.ims.core.auth.User;
import cm.gasmyr.app.ims.core.customer.Customer;
import cm.gasmyr.app.ims.core.provider.Provider;
import cm.gasmyr.app.ims.core.shop.Currency;
import cm.gasmyr.app.ims.core.store.Store;
import cm.gasmyr.app.ims.mail.MailConfig;
import cm.gasmyr.app.ims.mail.MailService;
import cm.gasmyr.app.ims.mail.MessageToSend;
import cm.gasmyr.app.ims.service.EncryptionService;
import cm.gasmyr.app.ims.service.auth.RoleService;
import cm.gasmyr.app.ims.service.auth.UserService;
import cm.gasmyr.app.ims.service.customer.CustomerService;
import cm.gasmyr.app.ims.service.provider.ProviderService;
import cm.gasmyr.app.ims.service.shop.CurrencyService;
import cm.gasmyr.app.ims.service.store.StoreService;

@RestController
@RequestMapping(value = "/api")
public class UserRestConroller {
	private static final Logger logger = LoggerFactory.getLogger(UserRestConroller.class);

	final UserService userService;
	final RoleService roleService;
	final EncryptionService encryptionService;
	final MailService mailService;
	final CurrencyService currencyService;
	final ProviderService providerService;
	final CustomerService customerService;
	final StoreService storeService;

	@Autowired
	public UserRestConroller(UserService userService, RoleService roleService, EncryptionService encryptionService,
			MailService mailService, CurrencyService currencyService, ProviderService providerService,
			CustomerService customerService, StoreService storeService) {
		super();
		this.userService = userService;
		this.roleService = roleService;
		this.encryptionService = encryptionService;
		this.mailService = mailService;
		this.currencyService = currencyService;
		this.providerService = providerService;
		this.customerService = customerService;
		this.storeService = storeService;
	}

	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public MyResponse deleteUser(@RequestBody Role role) {
		boolean status = true;
		try {
			userService.delete(role.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/currency", method = RequestMethod.DELETE)
	public MyResponse deleteCurrency(@RequestBody Currency currency) {
		boolean status = true;
		try {
			currencyService.delete(currency.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/provider", method = RequestMethod.DELETE)
	public MyResponse deleteProvider(@RequestBody Provider provider) {
		boolean status = true;
		try {
			providerService.delete(provider.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/store", method = RequestMethod.DELETE)
	public MyResponse deleteStore(@RequestBody Store store) {
		boolean status = true;
		try {
			storeService.delete(store.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.DELETE)
	public MyResponse deleteCustomer(@RequestBody Customer customer) {
		boolean status = true;
		try {
			customerService.delete(customer.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/role", method = RequestMethod.DELETE)
	public MyResponse deleteRole(@RequestBody User user) {
		boolean status = true;
		try {
			roleService.delete(user.getId());
		} catch (Exception e) {
			status = false;
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public MyResponse addUser(@RequestBody User user) {
		boolean status = true;
		try {
			user.setUsername(user.getEmail());
			user.setActive(true);
			user.addRole(roleService.findByName("ROLE_USER"));
			user.setEncryptedpassword(encryptionService.encryptString(user.getPassword()));
			if (user.canBeSave()) {
				userService.saveOrUpdate(user);
			}
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);

	}

	@RequestMapping(value = "/currency", method = RequestMethod.POST)
	public MyResponse addCurrency(@RequestBody Currency currency) {
		boolean status = true;
		try {
			if (currency.canBeSave()) {
				currencyService.saveOrUpdate(currency);
			}
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);

	}

	@RequestMapping(value = "/provider", method = RequestMethod.POST)
	public MyResponse addProvider(@RequestBody Provider provider) {
		boolean status = true;
		try {
			if (provider.canBeSave()) {
				providerService.saveOrUpdate(provider);
			}
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public MyResponse addCustomer(@RequestBody Customer customer) {
		boolean status = true;
		try {
			if (customer.canBeSave()) {
				customerService.saveOrUpdate(customer);
			}
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public MyResponse addStore(@RequestBody Store store) {
		boolean status = true;
		try {
			if (store.canBeSave()) {
				storeService.saveOrUpdate(store);
			}
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public MyResponse addRole(@RequestBody Role role) {
		boolean status = true;
		if (role.canBeSave()) {
			role = roleService.saveOrUpdate(role);
		} else {
			status = false;
		}
		return new MyResponse(status, role);
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public MyResponse resetUserPassword(@RequestBody User user) {
		User userToReset = null;
		boolean status = true;
		try {
			userToReset = userService.findByEmail(user.getEmail());
			MessageToSend message = new MessageToSend();
			message.setTo(userToReset.getEmail());
			message.setSubject("Password Reset");
			message.setText("<body><br><h2><center>Password reset</center></h2><br> <p><b style='color:blue;font-size:2em;font-familly:Arail;font-weight:2em;'>Hi, you receive this mail beacause you joss ask the reset of your password.</b> </p></body>");
			message.setFrom(MailConfig.FROM);
			mailService.sendHtmlMail(message);
		} catch (Exception e) {
			status = false;
		}
		return new MyResponse(status, userToReset);
	}

}
