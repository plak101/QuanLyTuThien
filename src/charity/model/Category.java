package charity.model;

public class Category {
    private int categoryId;
    private String categoryName;
    private boolean status;
    
    public Category() {
        this.status = true;
    }
    
    public Category(int categoryId, String categoryName, boolean status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.status = status;
    }
    
    public Category(String categoryName, boolean status) {
        this.categoryName = categoryName;
        this.status = status;
    }
    
    public int getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public boolean isStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return this.categoryName;
    }
} 