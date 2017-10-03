package cm.gasmyr.app.ims.service.notification;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.notification.ItemNotification;
import cm.gasmyr.app.ims.repository.notification.ItemNotificationRepository;

@Service
@Component
public class ItemNotificationServiceImpl implements ItemNotificationService {
	final ItemNotificationRepository itemNotificationRepository;

	@Autowired
	public ItemNotificationServiceImpl(ItemNotificationRepository itemNotificationRepository) {
		this.itemNotificationRepository = itemNotificationRepository;
	}

	@Override
	public List<ItemNotification> listAll() {
		return itemNotificationRepository.findAll();
	}

	@Override
	public ItemNotification getById(Long id) {
		return itemNotificationRepository.findOne(id);
	}

	@Override
	public ItemNotification saveOrUpdate(ItemNotification domainObject) {
		ItemNotification itemNotification = domainObject;
		if (domainObject.getId() != null) {
			itemNotification = itemNotificationRepository.findOne(domainObject.getId());
			if (itemNotification != null) {
				itemNotification.updateInternal(domainObject);
			}
		}
		return itemNotificationRepository.save(itemNotification);
	}

	@Override
	public void delete(Long id) {
		itemNotificationRepository.delete(id);
	}

}
