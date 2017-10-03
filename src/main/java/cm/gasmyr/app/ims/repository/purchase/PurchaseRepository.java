package cm.gasmyr.app.ims.repository.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.purchase.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
