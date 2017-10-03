package cm.gasmyr.app.ims.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
