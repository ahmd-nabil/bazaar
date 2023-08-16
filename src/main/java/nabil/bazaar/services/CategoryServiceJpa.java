package nabil.bazaar.services;

import lombok.RequiredArgsConstructor;
import nabil.bazaar.domain.Category;
import nabil.bazaar.exceptions.CategoryNotFoundException;
import nabil.bazaar.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Ahmed Nabil
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceJpa implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final PagingService pagingService;
    @Override
    public Page<Category> findAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = pagingService.getPageRequest(pageNumber, pageSize);
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> update(Long id, Category update) {
        if(!categoryRepository.existsById(id)) return Optional.empty();
        Category category = categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException(id));
        category.setName(update.getName());
        categoryRepository.save(category);
        return Optional.of(category);
    }

    @Override
    public boolean delete(Long id) {
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
