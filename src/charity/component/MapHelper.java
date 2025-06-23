package charity.component;

import charity.model.Category;
import charity.model.CharityEvent;
import charity.model.Organization;
import charity.model.User;
import java.util.Map;

import charity.service.*;
import charity.service.IService.*;
import java.util.HashMap;
import java.util.List;

public class MapHelper {

    //service
    private static ICategoryService categoryService = new CategoryService();
    private static IOrganizationService organizationService = new OrganizationService();
    private static ICharityEventService eventService = new CharityEventService();
    private static IUserService userService = new UserService();
    
    //cached
    private static Map<Integer, String> cacheCategoryNames = null;
    private static Map<Integer, String> cacheOrganizationNames = null;
    private static Map<Integer, String> cacheEventNames = null;
    private static Map<Integer, String> cacheUserNames = null;
    //category
    private static Map<Integer, String> getAllCategoryName() {
        if (cacheCategoryNames == null) {
            cacheCategoryNames = new HashMap<>();
            List<Category> categories = categoryService.getAllCategories();
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
    
    //Event
    private static Map<Integer, String> getAllEventName(){
        if (cacheEventNames == null){
            cacheEventNames = new HashMap<>();
            List<CharityEvent> events = eventService.getEventListCall();
            for (CharityEvent e : events){
                cacheEventNames.put(e.getId(), e.getName());
            }
        }
        return cacheEventNames;
    }
    public static String getEventName(int id){
        Map<Integer, String> allEventName= getAllEventName();
        return allEventName.getOrDefault(id, "Không xác định");
    }
    
    //User
    private static Map<Integer, String> getAllUserName(){
        if (cacheUserNames ==null){
            cacheUserNames = new HashMap<>();
            List<User> users = userService.getAllUser();
            for (User u: users){
                cacheUserNames.put(u.getId(), u.getName());
            }
        }
        return cacheUserNames;
    }
    
    public static String getUserName(int id){
        Map<Integer, String> allUserName = getAllUserName();
        return allUserName.getOrDefault(id, "Không tồn tại");
    }
    //lam moi cached
    public static void refreshCategoryCache(){
        cacheCategoryNames= null;
    }
    
    public static void refreshOrganizationCache(){
        cacheOrganizationNames =null;
    }
    public static void refreshEventCache(){
        cacheEventNames =null;
    }
    public static void refreshUserCache(){
        cacheUserNames = null;
    }

}
