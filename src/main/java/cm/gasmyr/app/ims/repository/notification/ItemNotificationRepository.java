package cm.gasmyr.app.ims.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.notification.ItemNotification;

@Repository
public interface ItemNotificationRepository extends JpaRepository<ItemNotification, Long> {

}
