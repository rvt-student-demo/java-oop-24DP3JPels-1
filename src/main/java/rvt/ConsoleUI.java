package rvt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.categoryDAO = new CategoryDAO();
        this.productDAO = new ProductDAO();
    }

    public void start() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║  Produktu un Kategoriju Sistēma   ║");
        System.out.println("╚════════════════════════════════════╝\n");

        boolean running = true;
        while (running) {
            showMainMenu();
            System.out.print("\nIzvēlnē opciju (1-5): ");
            String choice = "";
            try {
                if (scanner.hasNextLine()) {
                    choice = scanner.nextLine().trim();
                } else {
                    running = false;
                    break;
                }
            } catch (Exception e) {
                System.out.println("✓ Programma slēgta");
                running = false;
                break;
            }

            switch (choice) {
                case "1":
                    manageCategoriesMenu();
                    break;
                case "2":
                    manageProductsMenu();
                    break;
                case "3":
                    viewProductsByCategory();
                    break;
                case "4":
                    searchAndFilterMenu();
                    break;
                case "5":
                    System.out.println("✓ Programma slēgta");
                    running = false;
                    break;
                default:
                    System.out.println("✗ Nepareiza opcija!");
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\n┌─ Galvenā Izvēlne ─────────────────┐");
        System.out.println("│ 1. Kategoriju pārvaldīšana        │");
        System.out.println("│ 2. Produktu pārvaldīšana          │");
        System.out.println("│ 3. Produkti pa kategorijām        │");
        System.out.println("│ 4. Meklēt un filtrēt              │");
        System.out.println("│ 5. Iziet                          │");
        System.out.println("└───────────────────────────────────┘");
    }

    private void manageCategoriesMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n┌─ Kategoriju Pārvaldīšana ─────────┐");
            System.out.println("│ 1. Apskatīt kategorijas           │");
            System.out.println("│ 2. Pievienot kategoriju           │");
            System.out.println("│ 3. Rediģēt kategoriju             │");
            System.out.println("│ 4. Dzēst kategoriju               │");
            System.out.println("│ 5. Atpakaļ                        │");
            System.out.println("└───────────────────────────────────┘");
            System.out.print("Izvēlnē opciju (1-5): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    listCategories();
                    break;
                case "2":
                    addCategory();
                    break;
                case "3":
                    editCategory();
                    break;
                case "4":
                    deleteCategory();
                    break;
                case "5":
                    inMenu = false;
                    break;
                default:
                    System.out.println("✗ Nepareiza opcija!");
            }
        }
    }

    private void listCategories() {
        List<Category> categories = categoryDAO.getAllCategories();
        if (categories.isEmpty()) {
            System.out.println("\n✗ Kategorijas nav atrasti!");
        } else {
            System.out.println("\n┌─ Kategoriju Saraksts ─────────────┐");
            for (Category cat : categories) {
                System.out.println("│ " + cat);
            }
            System.out.println("└───────────────────────────────────┘");
        }
    }

    private void addCategory() {
        System.out.print("\nIevadiet kategorijas nosaukumu: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("✗ Nosaukums nedrīkst būt tukšs!");
            return;
        }
        categoryDAO.addCategory(new Category(name));
    }

    private void editCategory() {
        listCategories();
        System.out.print("\nIevadiet kategorijas ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Category cat = categoryDAO.getCategoryById(id);
            if (cat == null) {
                System.out.println("✗ Kategorija nav atrasta!");
                return;
            }
            System.out.print("Ievadiet jauno nosaukumu: ");
            String newName = scanner.nextLine().trim();
            if (newName.isEmpty()) {
                System.out.println("✗ Nosaukums nedrīkst būt tukšs!");
                return;
            }
            cat.setName(newName);
            categoryDAO.updateCategory(cat);
        } catch (NumberFormatException e) {
            System.out.println("✗ Nepareizs ID!");
        }
    }

    private void deleteCategory() {
        listCategories();
        System.out.print("\nIevadiet kategorijas ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            if (categoryDAO.haProducts(id)) {
                System.out.println("\n⚠ Šajā kategorijā ir produkti!");
                System.out.print("Vai dzēst arī produktus? (j/n): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("j")) {
                    productDAO.deleteProductsByCategory(id);
                    categoryDAO.deleteCategory(id);
                } else {
                    System.out.println("⚠ Dzēšana atcelta");
                }
            } else {
                categoryDAO.deleteCategory(id);
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Nepareizs ID!");
        }
    }

    private void manageProductsMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n┌─ Produktu Pārvaldīšana ───────────┐");
            System.out.println("│ 1. Apskatīt produktus             │");
            System.out.println("│ 2. Pievienot produktu             │");
            System.out.println("│ 3. Atjaunināt cenu                │");
            System.out.println("│ 4. Dzēst produktu                 │");
            System.out.println("│ 5. Atpakaļ                        │");
            System.out.println("└───────────────────────────────────┘");
            System.out.print("Izvēlnē opciju (1-5): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    listProducts();
                    break;
                case "2":
                    addProduct();
                    break;
                case "3":
                    updateProductPrice();
                    break;
                case "4":
                    deleteProduct();
                    break;
                case "5":
                    inMenu = false;
                    break;
                default:
                    System.out.println("✗ Nepareiza opcija!");
            }
        }
    }

    private void listProducts() {
        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("\n✗ Produkti nav atrasti!");
        } else {
            System.out.println("\n┌─ Produktu Saraksts ───────────────┐");
            for (Product prod : products) {
                System.out.println("│ " + prod);
            }
            System.out.println("└───────────────────────────────────┘");
        }
    }

    private void addProduct() {
        listCategories();
        System.out.print("\nIevadiet kategorijas ID: ");
        try {
            int categoryId = Integer.parseInt(scanner.nextLine().trim());
            Category cat = categoryDAO.getCategoryById(categoryId);
            if (cat == null) {
                System.out.println("✗ Kategorija nav atrasta!");
                return;
            }
            System.out.print("Ievadiet produkta nosaukumu: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("✗ Nosaukums nedrīkst būt tukšs!");
                return;
            }
            System.out.print("Ievadiet cenu: ");
            double price = Double.parseDouble(scanner.nextLine().trim());
            if (price < 0) {
                System.out.println("✗ Cena nedrīkst būt negatīva!");
                return;
            }
            productDAO.addProduct(new Product(name, price, categoryId));
        } catch (NumberFormatException e) {
            System.out.println("✗ Nepareizs ievads!");
        }
    }

    private void updateProductPrice() {
        listProducts();
        System.out.print("\nIevadiet produkta ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Product prod = productDAO.getProductById(id);
            if (prod == null) {
                System.out.println("✗ Produkts nav atrasts!");
                return;
            }
            System.out.print("Ievadiet jauno cenu: ");
            double newPrice = Double.parseDouble(scanner.nextLine().trim());
            if (newPrice < 0) {
                System.out.println("✗ Cena nedrīkst būt negatīva!");
                return;
            }
            productDAO.updateProductPrice(id, newPrice);
        } catch (NumberFormatException e) {
            System.out.println("✗ Nepareizs ievads!");
        }
    }

    private void deleteProduct() {
        listProducts();
        System.out.print("\nIevadiet produkta ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Product prod = productDAO.getProductById(id);
            if (prod == null) {
                System.out.println("✗ Produkts nav atrasts!");
                return;
            }
            productDAO.deleteProduct(id);
        } catch (NumberFormatException e) {
            System.out.println("✗ Nepareizs ID!");
        }
    }

    private void viewProductsByCategory() {
        listCategories();
        System.out.print("\nIevadiet kategorijas ID: ");
        try {
            int categoryId = Integer.parseInt(scanner.nextLine().trim());
            Category cat = categoryDAO.getCategoryById(categoryId);
            if (cat == null) {
                System.out.println("✗ Kategorija nav atrasta!");
                return;
            }
            List<Product> products = productDAO.getProductsByCategory(categoryId);
            if (products.isEmpty()) {
                System.out.println("\n✗ Šajā kategorijā nav produktu!");
            } else {
                System.out.println("\n┌─ Produkti kategorijā: " + cat.getName() + " ─┐");
                for (Product prod : products) {
                    System.out.println("│ " + prod);
                }
                System.out.println("└──────────────────────────────────┘");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Nepareizs ID!");
        }
    }

    private void searchAndFilterMenu() {
        System.out.println("\n┌─ Meklēšana un Filtrēšana ─────────┐");
        System.out.println("│ 1. Meklēt produktu pēc nosaukuma  │");
        System.out.println("│ 2. Filtrēt produktus pēc cenas    │");
        System.out.println("│ 3. Atpakaļ                        │");
        System.out.println("└───────────────────────────────────┘");
        System.out.print("Izvēlnē opciju (1-3): ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                searchProductByName();
                break;
            case "2":
                filterProductsByPrice();
                break;
            case "3":
                break;
            default:
                System.out.println("✗ Nepareiza opcija!");
        }
    }

    private void searchProductByName() {
        System.out.print("\nIevadiet produkta nosaukuma daļu: ");
        String searchTerm = scanner.nextLine().trim().toLowerCase();
        List<Product> allProducts = productDAO.getAllProducts();
        List<Product> results = new ArrayList<>();
        for (Product prod : allProducts) {
            if (prod.getName().toLowerCase().contains(searchTerm)) {
                results.add(prod);
            }
        }
        if (results.isEmpty()) {
            System.out.println("✗ Nav atrasti meklējamie produkti!");
        } else {
            System.out.println("\n┌─ Meklēšanas Rezultāti ────────────┐");
            for (Product prod : results) {
                System.out.println("│ " + prod);
            }
            System.out.println("└───────────────────────────────────┘");
        }
    }

    private void filterProductsByPrice() {
        try {
            System.out.print("\nIevadiet minimālo cenu: ");
            double minPrice = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Ievadiet maksimālo cenu: ");
            double maxPrice = Double.parseDouble(scanner.nextLine().trim());
            
            if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
                System.out.println("✗ Nepareizas cenas!");
                return;
            }

            List<Product> allProducts = productDAO.getAllProducts();
            List<Product> results = new ArrayList<>();
            for (Product prod : allProducts) {
                if (prod.getPrice() >= minPrice && prod.getPrice() <= maxPrice) {
                    results.add(prod);
                }
            }
            if (results.isEmpty()) {
                System.out.println("✗ Nav atrasti produkti šajā cenu diapazonā!");
            } else {
                System.out.println("\n┌─ Produkti cenu diapazonā ─────────┐");
                for (Product prod : results) {
                    System.out.println("│ " + prod);
                }
                System.out.println("└───────────────────────────────────┘");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Nepareizs ievads!");
        }
    }
}
