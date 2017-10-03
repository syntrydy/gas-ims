package cm.gasmyr.app.ims.service.sale;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.sale.SaleItem;
import cm.gasmyr.app.ims.repository.sale.SaleItemRepository;

@Service
@Component
public class SaleItemServiceImpl implements SaleItemService {

	SaleItemRepository saleItemRepository;

	@Autowired
	public SaleItemServiceImpl(SaleItemRepository saleItemRepository) {
		this.saleItemRepository = saleItemRepository;
	}

	@Override
	public List<SaleItem> listAll() {
		return saleItemRepository.findAll();
	}

	@Override
	public SaleItem getById(Long id) {
		return saleItemRepository.findOne(id);
	}

	@Override
	public SaleItem saveOrUpdate(SaleItem domainObject) {
		SaleItem sale = domainObject;
		if (domainObject.getId() != null) {
			sale = saleItemRepository.findOne(domainObject.getId());
			if (sale != null) {
				sale.updateInternal(domainObject);
			}
		}
		return saleItemRepository.save(sale);
	}

	@Override
	public void delete(Long id) {
		saleItemRepository.delete(id);
	}

}
