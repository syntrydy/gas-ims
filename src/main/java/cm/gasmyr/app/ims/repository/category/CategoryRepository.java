package cm.gasmyr.app.ims.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.gasmyr.app.ims.core.category.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
