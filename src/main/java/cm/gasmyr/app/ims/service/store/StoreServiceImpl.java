package cm.gasmyr.app.ims.service.store;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.store.Store;
import cm.gasmyr.app.ims.repository.store.StoreRepository;

@Service
@Component
public class StoreServiceImpl implements StoreService {
	final StoreRepository storeRepository;

	@Autowired
	public StoreServiceImpl(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}

	@Override
	public List<Store> listAll() {
		return (List<Store>) storeRepository.findAll();
	}

	@Override
	public Store getById(Long id) {
		return storeRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		storeRepository.delete(id);
	}

	@Override
	public Store saveOrUpdate(Store storeToSave) {
		Store store = storeToSave;
		if (storeToSave.getId() != null) {
			store = storeRepository.findOne(storeToSave.getId());
			if (store != null) {
				store.updateInternal(storeToSave);
			}
		}
		return storeRepository.save(store);
	}

}
