package cm.gasmyr.app.ims.repository.shop;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.shop.Currency;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

}
