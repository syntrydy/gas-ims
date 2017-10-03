package cm.gasmyr.app.ims.service.customer;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.customer.Customer;
import cm.gasmyr.app.ims.repository.customer.CustomerRepository;

@Service
@Component
public class CustomerServiceImpl implements CustomerService {
	final CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository currencyRepository) {
		this.customerRepository = currencyRepository;
	}

	@Override
	public List<Customer> listAll() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public Customer getById(Long id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Customer saveOrUpdate(Customer customerToUpdate) {
		Customer customer = customerToUpdate;
		if (customerToUpdate.getId() != null) {
			customer = customerRepository.findOne(customerToUpdate.getId());
			if (customer != null) {
				customer.updateInternal(customerToUpdate);
			}
		}
		return customerRepository.save(customer);
	}

	@Override
	public void delete(Long id) {
		customerRepository.delete(id);
	}
}
