package cm.gasmyr.app.ims.service.provider;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.provider.Provider;
import cm.gasmyr.app.ims.repository.provider.ProviderRepository;

@Service
@Component
public class ProviderServiceImpl implements ProviderService {
	final ProviderRepository providerRepository;

	@Autowired
	public ProviderServiceImpl(ProviderRepository categoryRepository) {
		this.providerRepository = categoryRepository;
	}

	@Override
	public List<Provider> listAll() {
		return (List<Provider>) providerRepository.findAll();
	}

	@Override
	public Provider getById(Long id) {
		return providerRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		providerRepository.delete(id);
	}

	@Override
	public Provider saveOrUpdate(Provider providerToSave) {
		Provider provider = providerToSave;
		if (providerToSave.getId() != null) {
			provider = providerRepository.findOne(providerToSave.getId());
			if (provider != null) {
				provider.updateInternal(providerToSave);
			}
		}
		return providerRepository.save(provider);
	}
}
