package cm.gasmyr.app.ims.service.category;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cm.gasmyr.app.ims.core.category.Category;
import cm.gasmyr.app.ims.repository.category.CategoryRepository;

@Service
@Component
public class CategoryServiceImpl implements CategoryService {

	final CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> listAll() {
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public Category getById(Long id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		categoryRepository.delete(id);
	}

	@Override
	public Category saveOrUpdate(Category categoryToSave) {
		Category category = categoryToSave;
		if (categoryToSave.getId() != null) {
			category = categoryRepository.findOne(categoryToSave.getId());
			if (category != null) {
				category.updateInternal(categoryToSave);
			}
		}
		return categoryRepository.save(category);
	}
}
