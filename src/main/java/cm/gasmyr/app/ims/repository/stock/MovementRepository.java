package cm.gasmyr.app.ims.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.stockmovement.StockMovement;

@Repository
public interface MovementRepository extends JpaRepository<StockMovement, Long> {

}
