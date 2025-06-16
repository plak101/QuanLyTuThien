package charity.repository;

import charity.model.Category;
import charity.repository.IRepository.ICategoryRepository;
import charity.utils.MessageDialog;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryRepository implements ICategoryRepository {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Category ORDER BY categoryId";
        try {
            connection = ConnectionDB.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("categoryId"),
                        rs.getString("categoryName")
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

            if (rs.next()) {
                return new Category(
                        rs.getInt("categoryId"),
                        rs.getString("categoryName")
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

            if (rs.next()) {
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
        String query = "INSERT INTO Category (categoryName) VALUES (?)";
        try {
            connection = ConnectionDB.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, category.getCategoryName());
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
        String query = "UPDATE Category SET categoryName = ? WHERE categoryId = ?";
        try {
            connection = ConnectionDB.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getCategoryId());
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
        } catch (SQLIntegrityConstraintViolationException e) {
            MessageDialog.showError("Danh mục hiện tại đang được dùng trong sự kiện. Không thể xóa!");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(connection, ps, null);
        }
    }

    private void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isCategoryNameExits(String name) {
        String sql = "SELECT 1 FROM Category WHERE categoryName=?";
        try (Connection conn = ConnectionDB.getConnection();) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

}
