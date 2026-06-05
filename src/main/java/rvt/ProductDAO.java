package rvt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public boolean addProduct(Product product) {
        String sql = "INSERT INTO products (name, price, category_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getCategoryId());
            pstmt.executeUpdate();
            System.out.println("✓ Produkts pievienots");
            return true;
        } catch (SQLException e) {
            System.err.println("Kļūda pievienojot produktu: " + e.getMessage());
            return false;
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.name as category_name FROM products p " +
                    "JOIN categories c ON p.category_id = c.id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product prod = new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getDouble("price"), rs.getInt("category_id"));
                prod.setCategoryName(rs.getString("category_name"));
                products.add(prod);
            }
        } catch (SQLException e) {
            System.err.println("Kļūda iegūstot produktus: " + e.getMessage());
        }
        return products;
    }

    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.name as category_name FROM products p " +
                    "JOIN categories c ON p.category_id = c.id WHERE p.category_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product prod = new Product(rs.getInt("id"), rs.getString("name"),
                            rs.getDouble("price"), rs.getInt("category_id"));
                    prod.setCategoryName(rs.getString("category_name"));
                    products.add(prod);
                }
            }
        } catch (SQLException e) {
            System.err.println("Kļūda iegūstot produktus: " + e.getMessage());
        }
        return products;
    }

    public Product getProductById(int id) {
        String sql = "SELECT p.*, c.name as category_name FROM products p " +
                    "JOIN categories c ON p.category_id = c.id WHERE p.id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Product prod = new Product(rs.getInt("id"), rs.getString("name"),
                            rs.getDouble("price"), rs.getInt("category_id"));
                    prod.setCategoryName(rs.getString("category_name"));
                    return prod;
                }
            }
        } catch (SQLException e) {
            System.err.println("Kļūda iegūstot produktu: " + e.getMessage());
        }
        return null;
    }

    public boolean updateProductPrice(int productId, double newPrice) {
        String sql = "UPDATE products SET price = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
            System.out.println("✓ Produkta cena atjaunināta");
            return true;
        } catch (SQLException e) {
            System.err.println("Kļūda atjauninot cenu: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✓ Produkts dzēsts");
            return true;
        } catch (SQLException e) {
            System.err.println("Kļūda dzēšot produktu: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteProductsByCategory(int categoryId) {
        String sql = "DELETE FROM products WHERE category_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);
            pstmt.executeUpdate();
            System.out.println("✓ Visi produkti tika dzēsti");
            return true;
        } catch (SQLException e) {
            System.err.println("Kļūda dzēšot produktus: " + e.getMessage());
            return false;
        }
    }
}
