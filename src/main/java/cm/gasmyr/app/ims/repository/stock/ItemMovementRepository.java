package cm.gasmyr.app.ims.repository.stock;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.stockmovement.ItemMovement;

@Repository
public interface ItemMovementRepository extends CrudRepository<ItemMovement, Long> {
}
