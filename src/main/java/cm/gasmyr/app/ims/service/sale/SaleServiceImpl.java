package cm.gasmyr.app.ims.service.sale;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.common.PaymentStatus;
import cm.gasmyr.app.ims.core.sale.Sale;
import cm.gasmyr.app.ims.repository.sale.SaleRepository;

@Service
@Component
public class SaleServiceImpl implements SaleService {

	final SaleRepository saleRepository;

	@Autowired
	public SaleServiceImpl(SaleRepository saleRepository) {
		super();
		this.saleRepository = saleRepository;
	}

	@Override
	public List<Sale> listAll() {
		return saleRepository.findAll();
	}

	@Override
	public Sale getById(Long id) {
		return saleRepository.findOne(id);
	}

	@Override
	public Sale saveOrUpdate(Sale domainObject) {
		Sale sale = domainObject;
		sale.setPaymentStatus(PaymentStatus.NOT_PAID);
		if (domainObject.getId() != null) {
			sale = saleRepository.findOne(domainObject.getId());
			if (sale != null) {
				sale.updateInternal(domainObject);
			}
		}
		return saleRepository.save(sale);
	}

	@Override
	public void delete(Long id) {
		saleRepository.delete(id);
	}

}
