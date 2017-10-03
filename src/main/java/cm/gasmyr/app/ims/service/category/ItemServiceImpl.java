package cm.gasmyr.app.ims.service.category;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.category.Item;
import cm.gasmyr.app.ims.repository.category.ItemRepository;

@Service
@Component
public class ItemServiceImpl implements ItemService {

	final ItemRepository itemRepository;

	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	public List<Item> listAll() {
		return itemRepository.findAll();
	}

	@Override
	public Item getById(Long id) {
		return itemRepository.findOne(id);
	}

	@Override
	public Item saveOrUpdate(Item domainObject) {
		Item item = domainObject;
		if (domainObject.getId() != null) {
			item = itemRepository.findOne(domainObject.getId());
			if (item != null) {
				item.updateInternal(domainObject);
			}
		}
		return itemRepository.save(item);
	}

	@Override
	public void delete(Long id) {
		itemRepository.delete(id);
	}

}
