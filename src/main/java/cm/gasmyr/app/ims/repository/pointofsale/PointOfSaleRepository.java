package cm.gasmyr.app.ims.repository.pointofsale;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.pointofsale.PointOfSale;

@Repository
public interface PointOfSaleRepository extends CrudRepository<PointOfSale, Long> {

}
