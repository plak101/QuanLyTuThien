package charity.repository;

import charity.model.Category;
import charity.repository.IRepository.ICategoryRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Category";
        try {
            connection = ConnectionDB.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                categories.add(new Category(
                    rs.getInt("categoryId"),
                    rs.getString("categoryName"),
                    rs.getBoolean("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, ps, rs);
        }
        return categories;
    }
    
    @Override
    public List<Category> getActiveCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Category WHERE status = TRUE";
        try {
            connection = ConnectionDB.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                categories.add(new Category(
                    rs.getInt("categoryId"),
                    rs.getString("categoryName"),
                    rs.getBoolean("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, ps, rs);
        }
        return categories;
    }
    
    @Override
    public Category getCategoryById(int id) {
        String query = "SELECT * FROM Category WHERE categoryId = ?";
        try {
            connection = ConnectionDB.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                return new Category(
                    rs.getInt("categoryId"),
                    rs.getString("categoryName"),
                    rs.getBoolean("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, ps, rs);
        }
        return null;
    }
    
    @Override
    public String getCategoryNameById(int id) {
        String query = "SELECT categoryName FROM Category WHERE categoryId = ?";
        try {
            connection = ConnectionDB.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                return rs.getString("categoryName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, ps, rs);
        }
        return null;
    }
    
    @Override
    public boolean addCategory(Category category) {
        String query = "INSERT INTO Category (categoryName, status) VALUES (?, ?)";
        try {
            connection = ConnectionDB.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, category.getCategoryName());
            ps.setBoolean(2, category.isStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(connection, ps, null);
        }
    }
    
    @Override
    public boolean updateCategory(Category category) {
        String query = "UPDATE Category SET categoryName = ?, status = ? WHERE categoryId = ?";
        try {
            connection = ConnectionDB.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, category.getCategoryName());
            ps.setBoolean(2, category.isStatus());
            ps.setInt(3, category.getCategoryId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(connection, ps, null);
        }
    }
    
    @Override
    public boolean deleteCategory(int id) {
        String query = "DELETE FROM Category WHERE categoryId = ?";
        try {
            connection = ConnectionDB.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(connection, ps, null);
        }
    }
    
    private void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 