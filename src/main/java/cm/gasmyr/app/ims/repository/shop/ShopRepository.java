package cm.gasmyr.app.ims.repository.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.shop.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

}
