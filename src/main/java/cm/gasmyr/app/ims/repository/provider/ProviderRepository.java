package cm.gasmyr.app.ims.repository.provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.provider.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

}
