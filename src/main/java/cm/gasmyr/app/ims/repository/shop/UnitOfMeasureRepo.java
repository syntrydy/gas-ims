package cm.gasmyr.app.ims.repository.shop;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.shop.UnitOfMeasure;

@Repository
public interface UnitOfMeasureRepo extends CrudRepository<UnitOfMeasure, Long> {

}
