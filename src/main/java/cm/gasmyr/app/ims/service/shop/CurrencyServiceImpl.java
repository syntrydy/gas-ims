package cm.gasmyr.app.ims.service.shop;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.shop.Currency;
import cm.gasmyr.app.ims.repository.shop.CurrencyRepository;

@Service
@Component
public class CurrencyServiceImpl implements CurrencyService {

	final CurrencyRepository currencyRepository;

	@Autowired
	public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	@Override
	public List<Currency> listAll() {
		return (List<Currency>) currencyRepository.findAll();
	}

	@Override
	public Currency getById(Long id) {
		return currencyRepository.findOne(id);
	}


	@Override
	public void delete(Long id) {
		currencyRepository.delete(id);
	}

	@Override
	public Currency saveOrUpdate(Currency currencyToSaveOrUpdate) {
		Currency currency = currencyToSaveOrUpdate;
		if (currencyToSaveOrUpdate.getId() != null) {
			currency = currencyRepository.findOne(currencyToSaveOrUpdate.getId());
			if (currency != null) {
				currency.updateInternal(currencyToSaveOrUpdate);
			}
		}
		return currencyRepository.save(currency);
	}
}
