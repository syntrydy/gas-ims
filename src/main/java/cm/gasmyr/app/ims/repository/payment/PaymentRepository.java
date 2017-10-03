package cm.gasmyr.app.ims.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.payment.SalePayment;

@Repository
public interface PaymentRepository extends JpaRepository<SalePayment, Long> {

}
