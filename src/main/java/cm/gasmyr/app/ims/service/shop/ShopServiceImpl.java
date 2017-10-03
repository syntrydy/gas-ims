package cm.gasmyr.app.ims.service.shop;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.shop.Shop;
import cm.gasmyr.app.ims.repository.shop.ShopRepository;

@Service
@Component
public class ShopServiceImpl implements ShopService{

	final ShopRepository shopRepository;

	@Autowired
	public ShopServiceImpl(ShopRepository currencyRepository) {
		this.shopRepository = currencyRepository;
	}

	@Override
	public List<Shop> listAll() {
		return (List<Shop>) shopRepository.findAll();
	}

	@Override
	public Shop getById(Long id) {
		return shopRepository.findOne(id);
	}


	@Override
	public void delete(Long id) {
		shopRepository.delete(id);
	}

	@Override
	public Shop saveOrUpdate(Shop shopToSave) {
		Shop shop = shopToSave;
		if (shopToSave.getId() != null) {
			shop = shopRepository.findOne(shopToSave.getId());
			if (shop != null) {
				shop.updateInternal(shopToSave);
			}
		}
		return shopRepository.save(shop);
	}
}
