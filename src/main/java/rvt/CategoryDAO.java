package rvt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private Connection connection;

    public CategoryDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public boolean addCategory(Category category) {
        String sql = "INSERT INTO categories (name) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, category.getName());
            pstmt.executeUpdate();
            System.out.println("✓ Kategorija pievienota");
            return true;
        } catch (SQLException e) {
            System.err.println("Kļūda pievienojot kategoriju: " + e.getMessage());
            return false;
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Category cat = new Category(rs.getInt("id"), rs.getString("name"));
                categories.add(cat);
            }
        } catch (SQLException e) {
            System.err.println("Kļūda iegūstot kategorijas: " + e.getMessage());
        }
        return categories;
    }

    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Category(rs.getInt("id"), rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Kļūda iegūstot kategoriju: " + e.getMessage());
        }
        return null;
    }

    public boolean updateCategory(Category category) {
        String sql = "UPDATE categories SET name = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, category.getName());
            pstmt.setInt(2, category.getId());
            pstmt.executeUpdate();
            System.out.println("✓ Kategorija atjaunināta");
            return true;
        } catch (SQLException e) {
            System.err.println("Kļūda atjauninot kategoriju: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCategory(int id) {
        String sql = "DELETE FROM categories WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✓ Kategorija dzēsta");
            return true;
        } catch (SQLException e) {
            System.err.println("Kļūda dzēšot kategoriju: " + e.getMessage());
            return false;
        }
    }

    public boolean haProducts(int categoryId) {
        String sql = "SELECT COUNT(*) FROM products WHERE category_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Kļūda pārbaudot produktus: " + e.getMessage());
        }
        return false;
    }
}
