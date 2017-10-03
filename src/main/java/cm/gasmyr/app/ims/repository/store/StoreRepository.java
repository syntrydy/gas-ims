package cm.gasmyr.app.ims.repository.store;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.store.Store;
@Repository
public interface StoreRepository extends CrudRepository<Store, Long>{

}
