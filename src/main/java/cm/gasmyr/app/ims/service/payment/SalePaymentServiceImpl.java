package cm.gasmyr.app.ims.service.payment;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.payment.SalePayment;
import cm.gasmyr.app.ims.repository.payment.PaymentRepository;

@Service
@Component
public class SalePaymentServiceImpl implements SalePaymentService {

	final PaymentRepository paymentRepository;

	@Autowired
	public SalePaymentServiceImpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@Override
	public List<SalePayment> listAll() {
		return paymentRepository.findAll();
	}

	@Override
	public SalePayment getById(Long id) {
		return paymentRepository.findOne(id);
	}

	@Override
	public SalePayment saveOrUpdate(SalePayment domainObject) {
		SalePayment payment = domainObject;
		if (domainObject.getId() != null) {
			payment = paymentRepository.findOne(domainObject.getId());
			if (payment != null) {
				payment.updateInternal(domainObject);
			}
		}
		return paymentRepository.save(payment);
	}

	@Override
	public void delete(Long id) {
		paymentRepository.delete(id);
	}

}
