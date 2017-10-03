package cm.gasmyr.app.ims.repository.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.sale.Sale;
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
