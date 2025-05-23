package charity.repository.IRepository;

import charity.model.Category;
import java.util.List;

public interface ICategoryRepository {
    List<Category> getAllCategories();
    List<Category> getActiveCategories();
    Category getCategoryById(int id);
    String getCategoryNameById(int id);
    boolean addCategory(Category category);
    boolean updateCategory(Category category);
    boolean deleteCategory(int id);
} 