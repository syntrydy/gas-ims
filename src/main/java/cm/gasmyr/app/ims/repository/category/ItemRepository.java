package cm.gasmyr.app.ims.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.category.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
