# Lietošanas Ceļvedis - Produktu un Kategoriju Pārvaldības Sistēma

## 🎯 Programmas Mērķis
Šī programma ļauj pārvaldīt produktus un to kategorijas SQLite datu bāzē caur vienkāršu konsolē saskarni.

---

## 🚀 Programmas Palaišana

### Prasības
- Java JDK 21 vai jaunāka versija
- Maven 3.6+

### Komandas

1. **Kompilēt projektu:**
   ```bash
   mvn clean compile
   ```

2. **Palaist programmu:**
   ```bash
   mvn exec:java -Dexec.mainClass="rvt.App"
   ```

3. **Izveidot un palaist JAR:**
   ```bash
   mvn package
   java -jar target/console-app-1.0-SNAPSHOT.jar
   ```

---

## 📋 Galvenā Izvēlne Opcijas

### 1. **Kategoriju Pārvaldīšana** 
Pārvaldiet produktu kategorijas.

**Apakšopcijas:**
- **Apskatīt kategorijas** - Parāda visas esošās kategorijas
- **Pievienot kategoriju** - Izveidojiet jaunu kategoriju
- **Rediģēt kategoriju** - Mainiet kategorijas nosaukumu
- **Dzēst kategoriju** - Noņemiet kategoriju (var dzēst arī tajā esošos produktus)

### 2. **Produktu Pārvaldīšana**
Pārvaldiet produktus un to informāciju.

**Apakšopcijas:**
- **Apskatīt produktus** - Parāda visus produktus ar kategoriju nosaukumiem
- **Pievienot produktu** - Izveidojiet jaunu produktu
- **Atjaunināt cenu** - Mainiet produkta cenu
- **Dzēst produktu** - Noņemiet produktu no datu bāzes

### 3. **Produkti pa Kategorijām**
Skatiet visus produktus noteiktā kategorijā.

### 4. **Meklēt un Filtrēt**
Meklējiet un filtrējiet produktus.

**Apakšopcijas:**
- **Meklēt produktu pēc nosaukuma** - Atrodiet produktus pēc daļas no nosaukuma
- **Filtrēt produktus pēc cenas** - Atrodiet produktus noteiktā cenu diapazonā

### 5. **Iziet**
Beigt programmu.

---

## 📝 Piemēri

### Pievienot Jaunu Kategoriju
1. Galvenā izvēlnē izvēlnē **1**
2. Izvēlnē **2** (Pievienot kategoriju)
3. Ievadiet kategorijas nosaukumu (piemēram: "Elektronika")

### Pievienot Produktu
1. Galvenā izvēlnē izvēlnē **2**
2. Izvēlnē **2** (Pievienot produktu)
3. Izvēlnē kategoriju no saraksta (piemēram: **1**)
4. Ievadiet produkta nosaukumu (piemēram: "Pārvietojas Telefons")
5. Ievadiet cenu (piemēram: **899.99**)

### Meklēt Produktu
1. Galvenā izvēlnē izvēlnē **4**
2. Izvēlnē **1** (Meklēt pēc nosaukuma)
3. Ievadiet meklējamā teksta daļu
4. Programma parāda atbilstošos produktus

---

## ⚠️ Validācija

Programma pārbauda:
- ✓ Tukšu nosaukumu ievadi
- ✓ Negatīvas cenas
- ✓ Kategoriju esamību
- ✓ Produktu esamību
- ✓ Cenu diapazonu filtrēšanai

---

## 🗂️ Dati Tiek Saglabāti

Visi dati tiek saglabāti SQLite datu bāzē faila vietā: `data/produkti.db`

Datu bāze tiek automātiski inicializēta programmas pirmajā palaišanā.

---

## 🔒 Drošība

- ✓ Visi SQL vaicājumi izmanto **PreparedStatement** (aizsargs pret SQL injection)
- ✓ Ievades validācija
- ✓ Kļūdu apstrāde

---

## 🐛 Problēmu Risināšana

**Problēma:** Programma neizpildās
- Pārbaudiet Java versiju: `java -version`
- Pārbaudiet Maven instalāciju: `mvn --version`

**Problēma:** Datu bāzes kļūda
- Pārbaudiet vai `data/` mape pastāv
- Dzēsiet `data/produkti.db` un palaistiet programmu no jauna

---

## 📞 Palīdzība

Jebkuras problēmas gadījumā kontaktējieties ar jūsu Java instruktoru.
