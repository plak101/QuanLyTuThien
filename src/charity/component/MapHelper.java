package charity.component;

import charity.model.Category;
import charity.model.Organization;
import java.util.Map;

import charity.service.CategoryService;
import charity.service.CharityEventService;
import charity.service.IService.ICategoryService;
import charity.service.IService.ICharityEventService;
import charity.service.IService.IOrganizationService;
import charity.service.OrganizationService;
import java.util.HashMap;
import java.util.List;

public class MapHelper {

    //service
    private static ICategoryService categoryService = new CategoryService();
    private static IOrganizationService organizationService = new OrganizationService();
    private static ICharityEventService eventService = new CharityEventService();
    
    //cached
    private static Map<Integer, String> cacheCategoryNames = null;
    private static Map<Integer, String> cacheOrganizationNames = null;

    //category
    private static Map<Integer, String> getAllCategoryName() {
        if (cacheCategoryNames == null) {
            cacheCategoryNames = new HashMap<>();
            List<Category> categories = categoryService.getActiveCategories();
            for (Category c : categories) {
                cacheCategoryNames.put(c.getCategoryId(), c.getCategoryName());
            }
        }

        return cacheCategoryNames;
    }

    public static String getCategoryName(int id){
        Map<Integer, String> allCategoryName = getAllCategoryName();
        return allCategoryName.getOrDefault(id, "Không xác định");
    }
    
    //Organization
    private static Map<Integer, String> getAllOrganizationName(){
        if (cacheOrganizationNames == null){
            cacheOrganizationNames = new HashMap<>();
            List<Organization> organizations = organizationService.getAllOrganization();
            for (Organization o : organizations){
                cacheOrganizationNames.put(o.getId(), o.getName());
            }
        }
        return cacheOrganizationNames;
    }
    public static String getOrganizationName(int id){
        Map<Integer, String> allOrganizationName= getAllOrganizationName();
        return allOrganizationName.getOrDefault(id, "Không xác định");
    }
    //lam moi cached
    public static void refreshCategoryCache(){
        cacheCategoryNames= null;
    }
    
    public static void refreshOrganizationCache(){
        cacheOrganizationNames =null;
    }

    
    
}
