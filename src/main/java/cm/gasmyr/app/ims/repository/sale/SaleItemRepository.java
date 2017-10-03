package cm.gasmyr.app.ims.repository.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.sale.SaleItem;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

}
