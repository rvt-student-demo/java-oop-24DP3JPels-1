# Produktu un Kategoriju Pārvaldības Sistēma
## Implementācijas Pārskats

### 📋 Projekta Stāvoklis: **PABEIGTS**

---

## ✅ Realizētie Komponenti

### 1. **Datu Modeļi**
- **Category.java** - Kategorija ar ID un nosaukumu
- **Product.java** - Produkts ar ID, nosaukumu, cenu, kategorijas ID un kategorijas nosaukumu

### 2. **Datu Piekļuves Objekti (DAO)**
- **CategoryDAO.java** - Pilna CRUD funkcionalitāte kategelijām
  - `addCategory()` - Jauna kategorija
  - `getAllCategories()` - Visu kategoriju saraksts
  - `getCategoryById()` - Kategorija pēc ID
  - `updateCategory()` - Kategorijas rediģēšana
  - `deleteCategory()` - Kategorijas dzēšana
  - `hasProducts()` - Pārbauda vai kategelijai ir produkti

- **ProductDAO.java** - Pilna CRUD funkcionalitāte produktiem
  - `addProduct()` - Jauns produkts
  - `getAllProducts()` - Visu produktu saraksts
  - `getProductsByCategory()` - Produkti pēc kategorijas
  - `getProductById()` - Produkts pēc ID
  - `updateProductPrice()` - Cenas atjaunināšana
  - `deleteProduct()` - Produkta dzēšana
  - `deleteProductsByCategory()` - Visu produktu dzēšana kategelijā

### 3. **Datu Bāze**
- **DatabaseConnection.java** - SQLite savienojuma vadīšana
  - Tabulas: `categories`, `products`
  - Ārējā atslēga (Foreign Key) starp `products.category_id` un `categories.id`

### 4. **Lietotāja Saskarne**
- **ConsoleUI.java** - Interaktīvā konsolē saskarne ar izvēļņu sistēmu
  - Kategoriju pārvaldīšana (CRUD)
  - Produktu pārvaldīšana (CRUD)
  - Produkti pa kategorijām
  - Meklēšana un filtrēšana

### 5. **Ieejas Punkts**
- **App.java** - Programmas sākums

---

## 🔒 Drošības Līdzekļi

✅ **PreparedStatement** - Visu vaicājumu aizsardzība pret SQL injection
✅ **Ievades validācija:**
  - Tukšu nosaukumu pārbaude
  - Negatīvu cenu pārbaude
  - Kategoriju un produktu esamības pārbaude
  - Min/max cenu validācija filtrēšanai

---

## 💾 Datu Bāzes Struktūra

### Tabula: `categories`
| Kolonna | Tips | Apraksts |
|---------|------|----------|
| id | INTEGER (PK) | Unikāls identifikators |
| name | TEXT | Kategorijas nosaukums (UNIQUE) |

### Tabula: `products`
| Kolonna | Tips | Apraksts |
|---------|------|----------|
| id | INTEGER (PK) | Unikāls identifikators |
| name | TEXT | Produkta nosaukums |
| price | REAL | Produkta cena |
| category_id | INTEGER (FK) | Atsauce uz kategoriju |

---

## 📱 Galvenā Izvēlne

```
1. Kategoriju pārvaldīšana
   ├─ Apskatīt kategorijas
   ├─ Pievienot kategoriju
   ├─ Rediģēt kategoriju
   └─ Dzēst kategoriju

2. Produktu pārvaldīšana
   ├─ Apskatīt produktus
   ├─ Pievienot produktu
   ├─ Atjaunināt cenu
   └─ Dzēst produktu

3. Produkti pa kategorijām

4. Meklēt un filtrēt
   ├─ Meklēt produktu pēc nosaukuma
   └─ Filtrēt produktus pēc cenas

5. Iziet
```

---

## 🚀 Programmas Palaišana

```bash
# Kompilēšana
mvn clean compile

# Palaišana
mvn exec:java -Dexec.mainClass="rvt.App"

# Alternativs - izveidot JAR un palaist
mvn package
java -jar target/console-app-1.0-SNAPSHOT.jar
```

---

## 🧪 Testēšana

Programma ir testēta ar šādi funkcionāla:

✅ Datu bāzes inicializācija
✅ Kategoriju pievienošana un dzēšana
✅ Produktu pievienošana un dzēšana
✅ Kategorijas dzēšana ar produktiem
✅ Produktu iegūšana pa kategorijām
✅ Meklēšana un filtrēšana
✅ Cenu atjaunināšana
✅ Ievades validācija
✅ SQL injection aizsardzība

---

## 📝 Tehniskā Informācija

- **Programmēšanas valoda:** Java 21
- **Datu bāze:** SQLite
- **Build Tool:** Maven
- **JDBC Version:** Iebūvēts Java SDK
- **Datu bāzes fails:** `data/produkti.db`

---

## 🎯 Izpildītās Prasības

- ✅ Divas tabulas ar PK/FK saistībām
- ✅ Datu pievienošana un rediģēšana
- ✅ PreparedStatement lietošana
- ✅ Konsolē saskarnes izvēlnes
- ✅ Kategoriju pievienošana un dzēšana
- ✅ Produktu pievienošana, rediģēšana, iegūšana
- ✅ Produktu validācija (nosaukums, cena > 0)
- ✅ Kategorijas dzēšana ar produktu apstrādi
- ✅ Datu attēlošana tabulārajā formātā
- ✅ Meklēšana un filtrēšana
