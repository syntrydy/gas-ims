package cm.gasmyr.app.ims.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cm.gasmyr.app.ims.core.category.Category;
import cm.gasmyr.app.ims.core.category.Item;
import cm.gasmyr.app.ims.service.category.CategoryService;
import cm.gasmyr.app.ims.service.category.ItemService;
import cm.gasmyr.app.ims.util.Utils;

@Controller
public class CategoryController {
	final CategoryService categoryService;
	final ItemService itemService;

	@Autowired
	public CategoryController(CategoryService categoryService, ItemService itemService) {
		this.categoryService = categoryService;
		this.itemService = itemService;
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String users(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("categories", categoryService.listAll());
		return "CategoryListPage";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/category/{id}")
	public String gotodetail(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("category", categoryService.getById(id));

		return "CategoryDetailPage";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/category")
	public String addPage(Model model, Principal principal) {
		model.addAttribute("category", new Category());
		model.addAttribute("categories", categoryService.listAll());
		return "CategoryAddPage";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/category")
	public String save(@Valid Category category, Model model, Principal principal) {
		if (category.canBeSave()) {
			categoryService.saveOrUpdate(category);
		}
		model.addAttribute("categories", categoryService.listAll());
		return "redirect:/categories";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/ecategory/{id}")
	public String gotoedit(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("category", categoryService.getById(id));
		List<Category> categories = categoryService.listAll();
		Category category = categoryService.getById(id);
		categories.remove(category);
		model.addAttribute("category", category);
		model.addAttribute("categories", categories);
		return "CategoryEditPage";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ecategory")
	public String edit(@Valid Category category, Model model, Principal principal) {
		if (category.canBeSave()) {
			categoryService.saveOrUpdate(category);
		}
		model.addAttribute("categories", categoryService.listAll());
		return "redirect:/categories";
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String user(Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("items", itemService.listAll());
		model.addAttribute("categories", categoryService.listAll());
		return "ItemListPage";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/eitem/{id}")
	public String gotoeditItem(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("item", itemService.getById(id));
		model.addAttribute("categories", categoryService.listAll());
		return "ItemEditPage";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/item/{id}")
	public String gotodetailitem(@PathVariable("id") Long id, Model model, Principal principal) {
		model.addAttribute("username", Utils.getUserName(principal));
		model.addAttribute("item", itemService.getById(id));
		return "ItemDetailPage";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/eitem")
	public String editItem(@Valid Item item, Model model, Principal principal) {
		if (item.canBeSave()) {
			itemService.saveOrUpdate(item);
		}
		model.addAttribute("items", itemService.listAll());
		return "redirect:/items";
	}

}
