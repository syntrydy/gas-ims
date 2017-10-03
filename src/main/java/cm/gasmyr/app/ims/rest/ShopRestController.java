package cm.gasmyr.app.ims.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cm.gasmyr.app.ims.core.category.Category;
import cm.gasmyr.app.ims.core.category.Item;
import cm.gasmyr.app.ims.core.pointofsale.PointOfSale;
import cm.gasmyr.app.ims.core.shop.UnitOfMeasure;
import cm.gasmyr.app.ims.service.category.CategoryService;
import cm.gasmyr.app.ims.service.category.ItemService;
import cm.gasmyr.app.ims.service.category.UnitOfMeasureService;
import cm.gasmyr.app.ims.service.pointofsale.PointOfSaleService;

@RestController
@RequestMapping(value = "/api")
public class ShopRestController {
	private static final String MEASURE_BASE = "BASE";

	private static final Logger logger = LoggerFactory.getLogger(ShopRestController.class);

	final CategoryService categoryService;
	final ItemService itemService;
	final PointOfSaleService pointOfSaleService;
	final UnitOfMeasureService unitOfMeasureService;

	public ShopRestController(CategoryService categoryService, ItemService itemService,
			PointOfSaleService pointOfSaleService, UnitOfMeasureService unitOfMeasureService) {
		super();
		this.categoryService = categoryService;
		this.itemService = itemService;
		this.pointOfSaleService = pointOfSaleService;
		this.unitOfMeasureService = unitOfMeasureService;
	}

	@RequestMapping(value = "/category", method = RequestMethod.DELETE)
	public MyResponse deleteCategory(@RequestBody Category category) {
		boolean status = true;
		try {
			categoryService.delete(category.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/item", method = RequestMethod.DELETE)
	public MyResponse deleteItem(@RequestBody Item item) {
		boolean status = true;
		try {
			itemService.delete(item.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/pofsale", method = RequestMethod.DELETE)
	public MyResponse deletePointOfSale(@RequestBody PointOfSale pointOfSale) {
		boolean status = true;
		try {
			pointOfSaleService.delete(pointOfSale.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/measure", method = RequestMethod.DELETE)
	public MyResponse deleteMeasure(@RequestBody UnitOfMeasure unitOfMeasure) {
		boolean status = true;
		try {
			unitOfMeasureService.delete(unitOfMeasure.getId());
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public MyResponse addCategory(@RequestBody Category category) {
		boolean status = true;
		try {
			if (category.canBeSave()) {
				categoryService.saveOrUpdate(category);
			}
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/item", method = RequestMethod.POST)
	public MyResponse addItem(@RequestBody Item item) {
		boolean status = true;
		try {
			if (item.getCategory() == null) {
				item.setCategory(categoryService.getById(1l));
			} else {
				Long catId = item.getCategory().getId();
				item.setCategory(categoryService.getById(catId));
			}
			if (item.canBeSave()) {
				itemService.saveOrUpdate(item);
			}
			UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
			unitOfMeasure.setItem(item);
			unitOfMeasure.setName(item.getName() + MEASURE_BASE);
			unitOfMeasure.setQuantity(1);
			unitOfMeasureService.saveOrUpdate(unitOfMeasure);
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/measure", method = RequestMethod.POST)
	public MyResponse addMeasure(@RequestBody UnitOfMeasure unitOfMeasure) {
		boolean status = true;
		try {
			if (unitOfMeasure.getItem() == null) {
				unitOfMeasure.setItem(itemService.getById(1l));
			} else {
				Long itemId = unitOfMeasure.getItem().getId();
				unitOfMeasure.setItem(itemService.getById(itemId));
			}
			if (unitOfMeasure.canBeSave()) {
				unitOfMeasureService.saveOrUpdate(unitOfMeasure);
			}
		} catch (Exception e) {
			status = false;
			logger.error(e.getMessage());
		}
		return new MyResponse(status, null);
	}

	@RequestMapping(value = "/itemmeasure", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<List<UnitOfMeasure>> getMeasureOfItem(@RequestBody Item item) {
		try {
			item = itemService.getById(item.getId());
			item.getUnitOfMeasures().stream().forEach(u -> u.setItem(null));
			return new ResponseEntity<List<UnitOfMeasure>>(new ArrayList<>(item.getUnitOfMeasures()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<List<UnitOfMeasure>>(new ArrayList<>(), HttpStatus.NO_CONTENT);
		}
	}
}
