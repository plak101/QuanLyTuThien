package charity.service;

import charity.model.Category;
import charity.repository.CategoryRepository;
import charity.service.IService.ICategoryService;
import java.util.List;

public class CategoryService implements ICategoryService {
    private CategoryRepository categoryRepository;
    
    public CategoryService() {
        this.categoryRepository = new CategoryRepository();
    }
    
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }
    
    @Override
    public List<Category> getActiveCategories() {
        return categoryRepository.getActiveCategories();
    }
    
    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.getCategoryById(id);
    }
    
    @Override
    public String getCategoryNameById(int id) {
        return categoryRepository.getCategoryNameById(id);
    }
    
    @Override
    public boolean addCategory(Category category) {
        return categoryRepository.addCategory(category);
    }
    
    @Override
    public boolean updateCategory(Category category) {
        return categoryRepository.updateCategory(category);
    }
    
    @Override
    public boolean deleteCategory(int id) {
        return categoryRepository.deleteCategory(id);
    }
} 