# 🏭 Supply Chain Management System with QR-Based Employee Attendance Tracking

**CSC 305: Software Engineering - Term 1 (2025/2026)**

An integrated JavaFX desktop application that combines supply chain management with QR code-based employee attendance tracking.

---

## 👥 Team Members

| Name | Student ID | Role |
|------|-----------|------|
| Jawad Ali Alnatah | 2240002923 | Team Leader & Backend Developer |
| Mustafa AbdulKarim AbdRabAlameer | 2240002959 | Backend Developer & Database Designer |
| Abdullah Jaffer Masiri | 2240004545 | UI/UX Designer |
| Ahmed Hussain Alghazwe | 2240002359 | Frontend Developer |
| Abdullah Abdulaziz Alhamadi | 2240003012 | Frontend Developer & GUI Designer |
| Mohammad Khalid Alqallaf | 2240005145 | Quality Assurance & Documentation |

**Advisor:** Saeed Matar Alshahrani

---

## 📋 Table of Contents

- [Project Overview](#-project-overview)
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Project Structure](#-project-structure)
- [Setup Instructions](#-setup-instructions)
- [How to Run](#-how-to-run)
- [Current Progress](#-current-progress)
- [Next Steps](#-next-steps)
- [Development Workflow](#-development-workflow)
- [Troubleshooting](#-troubleshooting)
- [Documentation](#-documentation)

---

## 🎯 Project Overview

This project aims to develop a **JavaFX desktop application** that combines:
- **Supply Chain Management**: Track inventory, manage suppliers, handle purchase orders
- **Employee Attendance Tracking**: QR code-based check-in/check-out system via smartphone web interface

### Key Objectives

- Develop comprehensive supply chain management system
- Implement secure QR code-based attendance system
- Integrate supply management and attendance modules
- Create role-based access control (Admin, Manager, Employee, Supplier)
- Generate comprehensive reports and analytics

---

## ✨ Features

### ✅ Implemented Features

- [x] **User Authentication System**
  - Login functionality with username/password
  - BCrypt password hashing for security
  - Role-based access control

- [x] **Database Integration**
  - Connected to TiDB Cloud (MySQL-compatible)
  - User management system
  - Database connection pooling

- [x] **JavaFX GUI**
  - Login screen with FXML
  - Modern CSS styling
  - Responsive design

### 🚧 In Progress

- [ ] Dashboard interface
- [ ] Inventory management module
- [ ] Supplier management
- [ ] Employee management
- [ ] QR code generation system
- [ ] Web-based QR scanner

### 📅 Planned Features

- [ ] Purchase order system
- [ ] Attendance tracking
- [ ] Reporting and analytics
- [ ] Admin panel
- [ ] Export to Excel/PDF

---

## 🛠️ Technology Stack

### Core Technologies

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 17 | Programming language |
| **JavaFX** | 21.0.1 | GUI framework |
| **Maven** | 3.9.11 | Build automation & dependency management |
| **MySQL/TiDB** | 8.0+ | Database (Cloud-hosted on TiDB) |

### Key Libraries

| Library | Version | Purpose |
|---------|---------|---------|
| `mysql-connector-j` | 8.2.0 | Database connectivity |
| `zxing` | 3.5.3 | QR code generation |
| `jbcrypt` | 0.4 | Password hashing |
| `commons-lang3` | 3.14.0 | Utility functions |
| `gson` | 2.10.1 | JSON processing |
| `apache-poi` | 5.2.5 | Excel report generation |
| `itext` | 5.5.13.3 | PDF report generation |

### Development Tools

- **IDE**: IntelliJ IDEA / Visual Studio Code
- **Version Control**: Git + GitHub
- **Database**: TiDB Cloud (MySQL compatible)

---

## 📁 Project Structure

```
Supply-Chain-Management-System/
│
├── pom.xml                          # Maven configuration
├── README.md                        # This file
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/team/supplychain/
│   │   │       │
│   │   │       ├── Main.java                    # Application entry point
│   │   │       │
│   │   │       ├── controllers/                 # UI Controllers (MVC)
│   │   │       │   ├── LoginController.java     # ✅ Done
│   │   │       │   ├── DashboardController.java # ✅ Done
│   │   │       │   ├── InventoryController.java # 🚧 TODO
│   │   │       │   ├── SupplierController.java  # 🚧 TODO
│   │   │       │   └── EmployeeController.java  # 🚧 TODO
│   │   │       │
│   │   │       ├── models/                      # Data models (POJOs)
│   │   │       │   ├── User.java                # ✅ Done
│   │   │       │   ├── Item.java                # 🚧 TODO
│   │   │       │   ├── Supplier.java            # 🚧 TODO
│   │   │       │   ├── Employee.java            # 🚧 TODO
│   │   │       │   └── PurchaseOrder.java       # 🚧 TODO
│   │   │       │
│   │   │       ├── dao/                         # Database Access Objects
│   │   │       │   ├── UserDAO.java             # ✅ Done
│   │   │       │   ├── ItemDAO.java             # 🚧 TODO
│   │   │       │   ├── SupplierDAO.java         # 🚧 TODO
│   │   │       │   └── EmployeeDAO.java         # 🚧 TODO
│   │   │       │
│   │   │       ├── utils/                       # Utility classes
│   │   │       │   ├── DatabaseConnection.java  # ✅ Done
│   │   │       │   ├── PasswordUtil.java        # ✅ Done
│   │   │       │   ├── AlertUtil.java           # ✅ Done
│   │   │       │   └── QRCodeGenerator.java     # 🚧 TODO
│   │   │       │
│   │   │       └── enums/                       # Enumerations
│   │   │           └── UserRole.java            # ✅ Done
│   │   │
│   │   └── resources/
│   │       ├── fxml/                            # FXML UI layouts
│   │       │   ├── Login.fxml                   # ✅ Done
│   │       │   ├── Dashboard.fxml               # ✅ Done
│   │       │   ├── Inventory.fxml               # 🚧 TODO
│   │       │   └── Suppliers.fxml               # 🚧 TODO
│   │       │
│   │       ├── css/                             # Stylesheets
│   │       │   └── styles.css                   # ✅ Done
│   │       │
│   │       ├── images/                          # Images and icons
│   │       │   └── logo.png                     # 🚧 TODO
│   │       │
│   │       └── config.properties                # Configuration file
│   │
│   └── test/                                    # Unit tests
│       └── java/
│           └── com/team/supplychain/
│               └── UserDAOTest.java             # 🚧 TODO
│
└── target/                                      # Build output (auto-generated)
    └── supply-chain-management-1.0.0.jar
```

---

## 🚀 Setup Instructions

### Prerequisites

Before you start, make sure you have:

- ✅ Windows 10/11 (or macOS/Linux)
- ✅ Internet connection
- ✅ Administrator access (for installing Maven)

### Step 1: Install Java 17

1. **Check if Java is installed:**
   ```powershell
   java -version
   ```

2. **If not installed, download and install:**
   - Download from: https://adoptium.net/temurin/releases/
   - Choose: **Java 17 (LTS)**
   - Install using default settings

3. **Verify installation:**
   ```powershell
   java -version
   # Should show: openjdk version "17.x.x"
   ```

---

### Step 2: Install Maven

1. **Open PowerShell as Administrator:**
   - Press `Win + X`
   - Select "Windows PowerShell (Admin)"

2. **Install Chocolatey (package manager):**
   ```powershell
   Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
   ```

3. **Install Maven using Chocolatey:**
   ```powershell
   choco install maven -y
   ```

4. **Close and reopen PowerShell, then verify:**
   ```powershell
   mvn -version
   # Should show: Apache Maven 3.9.x
   ```

---

### Step 3: Clone the Repository

```powershell
# Navigate to your desired folder
cd Desktop

# Clone the project
git clone https://github.com/JawadAlnatah/Supply-Chain-Management-System-With-QR-Based-Attendance-Employee-Tracking-.git

# Navigate into project folder
cd Supply-Chain-Management-System-With-QR-Based-Attendance-Employee-Tracking-
```

---

### Step 4: Configure Database Connection

1. **Open the config file:**
   ```
   src/main/resources/config.properties
   ```

2. **Update with TiDB credentials:**
   ```properties
   db.url=jdbc:mysql://gateway01.eu-central-1.prod.aws.tidbcloud.com:4000/supply_chain_qr?sslMode=VERIFY_IDENTITY&useSSL=true
   db.username=3uB8fqJmu4peKdN.root
   db.password=46dmNGakAQIh5Q0v
   db.driver=com.mysql.cj.jdbc.Driver
   ```

   > ⚠️ **Note:** These credentials are already in the project. Don't change them unless instructed.

---

### Step 5: Download Dependencies

```powershell
# Make sure you're in project root (folder with pom.xml)
cd "path/to/Supply-Chain-Management-System-With-QR-Based-Attendance-Employee-Tracking-"

# Download all dependencies (first time - takes 2-5 minutes)
mvn clean install
```

**Expected output:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: 45.123 s
```

---

## ▶️ How to Run

### Method 1: Using Maven (Recommended)

```powershell
# Make sure you're in project directory
cd "C:\Users\YOUR_USERNAME\Desktop\Supply-Chain-Management-System-With-QR-Based-Attendance-Employee-Tracking-"

# Run the application
mvn javafx:run
```

**The GUI window should appear!** 🎉

---


## 🔐 Login Credentials

Use these credentials to test the application:

| Username | Password | Role |
|----------|----------|------|
| `admin` | `password123` | Administrator |
| `manager1` | `password123` | Manager |
| `employee1` | `employee123` | Employee |
| `supplier1` | `supplier123` | Supplier |

---

## ✅ Current Progress

### Week 3-4: Project Planning ✅
- [x] Project idea defined
- [x] Team roles assigned
- [x] Project proposal submitted

### Week 5-8: Initial Setup ✅
- [x] GitHub repository created
- [x] Project structure established
- [x] Maven configuration (pom.xml)
- [x] Database schema designed
- [x] TiDB Cloud database setup
- [x] SPMP document submitted

### Week 9-10: Core Development ✅
- [x] Database connection established
- [x] User authentication system
- [x] Login screen (JavaFX + FXML)
- [x] Password encryption (BCrypt)
- [x] Basic DAO pattern implementation

### Current Status (Week 10)

**✅ Working:**
- JavaFX application launches
- Login screen displays correctly
- Database connection successful
- User authentication functional
- CSS styling applied

**🐛 Known Issues:**
- Password hashing needs database update (BCrypt format)
- Dashboard navigation not yet implemented

---

## 📝 Next Steps

### Week 11: Requirements Documentation
- [ ] Complete SRS document
- [ ] Define all functional requirements
- [ ] Create use case diagrams
- [ ] Finalize database schema

### Week 11-12: Dashboard & Navigation
- [ ] Implement main dashboard UI
- [ ] Create navigation menu
- [ ] Implement role-based access control
- [ ] Add logout functionality

### Week 12-13: Inventory Module
- [ ] Create Item model and DAO
- [ ] Implement inventory CRUD operations
- [ ] Design inventory UI (FXML)
- [ ] Add stock alerts functionality
- [ ] Complete SDD document

### Week 13-14: Supplier Module
- [ ] Create Supplier model and DAO
- [ ] Implement supplier management
- [ ] Design supplier UI
- [ ] Add purchase order system

### Week 14-15: Employee & QR System
- [ ] Create Employee model and DAO
- [ ] Implement QR code generation
- [ ] Create web-based QR scanner
- [ ] Implement attendance tracking
- [ ] Complete STS document

### Week 15-16: Testing & Polish
- [ ] Write unit tests
- [ ] Integration testing
- [ ] Bug fixes
- [ ] Performance optimization
- [ ] User documentation
- [ ] Final presentation preparation

---

## 👨‍💻 Development Workflow

### For Team Members: Getting Started

1. **Clone the repository:**
   ```powershell
   git https://github.com/JawadAlnatah/Supply-Chain-Management-System-With-QR-Based-Attendance-Employee-Tracking-.git
   cd Supply-Chain-Management-System-With-QR-Based-Attendance-Employee-Tracking-
   ```

2. **Install dependencies:**
   ```powershell
   mvn clean install
   ```

3. **Create your feature branch:**
   ```powershell
   git checkout -b feature/your-feature-name
   # Example: git checkout -b feature/inventory-module
   ```

4. **Make your changes**

5. **Test your changes:**
   ```powershell
   mvn clean compile
   mvn javafx:run
   ```

6. **Commit and push:**
   ```powershell
   git add .
   git commit -m "Description of your changes"
   git push origin feature/your-feature-name
   ```

7. **Create Pull Request on GitHub**

---

### Git Workflow Best Practices

```
main (protected)
  └── develop
      ├── feature/inventory-module (Mustafa)
      ├── feature/supplier-ui (Abdullah J.)
      ├── feature/dashboard (Ahmed)
      └── feature/qr-system (Abdullah A.)
```

**Branch Naming Convention:**
- `feature/description` - New features
- `bugfix/description` - Bug fixes
- `docs/description` - Documentation updates

**Commit Message Format:**
```
feat: Add inventory management module
fix: Resolve login button not responding
docs: Update README with setup instructions
style: Apply new CSS to dashboard
```

---

## 🛠️ Common Maven Commands

```powershell
# Clean build directory
mvn clean

# Compile source code
mvn compile

# Run tests
mvn test

# Package as JAR
mvn package

# Install to local repository
mvn install

# Run the application
mvn javafx:run

# Clean, compile, and run
mvn clean compile javafx:run

# Skip tests
mvn install -DskipTests

# Update dependencies
mvn clean install -U
```

---

## 🐛 Troubleshooting

### Issue 1: "mvn: command not found"

**Solution:**
```powershell
# Reinstall Maven
choco install maven -y

# Restart PowerShell
# Verify installation
mvn -version
```

---

### Issue 2: "Cannot find pom.xml"

**Solution:**
```powershell
# Make sure you're in the correct directory
cd "path/to/Supply-Chain-Management-System-With-QR-Based-Attendance-Employee-Tracking-"

# Verify pom.xml exists
ls pom.xml
```

---

### Issue 3: "JavaFX runtime components are missing"

**Solution:**
```powershell
# ALWAYS use Maven to run
mvn javafx:run

# DON'T use VSCode Run button without proper configuration
```

---

### Issue 4: Database Connection Failed

**Solution:**
1. Check internet connection
2. Verify credentials in `config.properties`
3. Check TiDB Cloud dashboard is accessible
4. Verify database name: `supply_chain_qr`

---

### Issue 5: "Invalid salt version" (Password Error)

**Solution:**
Database passwords need to be BCrypt hashed. Run this SQL:

```sql
-- Update passwords to BCrypt format
UPDATE users 
SET password_hash = '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'
WHERE username IN ('admin', 'manager1', 'employee1', 'supplier1');
```

---

### Issue 6: Build Failures

**Solution:**
```powershell
# Clean and rebuild
mvn clean install -U

# If still fails, delete Maven cache
rm -r ~/.m2/repository
mvn clean install
```

---

## 📚 Documentation

### Project Documents

- [x] **Week 3:** Project Idea Form
- [x] **Week 4:** Project Proposal
- [x] **Week 8:** Software Project Management Plan (SPMP)
- [ ] **Week 11:** Software Requirements Specification (SRS)
- [ ] **Week 13:** Software Design Description (SDD)
- [ ] **Week 15:** Software Test Specification (STS)

### Technical Documentation

- **API Documentation:** Generate using Javadoc
  ```powershell
  mvn javadoc:javadoc
  # Output: target/site/apidocs/index.html
  ```

- **Database Schema:** See `docs/database_schema.sql`

---

## 🎓 Learning Resources

### JavaFX
- [Official JavaFX Documentation](https://openjfx.io/)
- [JavaFX Tutorial - Jenkov](http://tutorials.jenkov.com/javafx/index.html)

### Maven
- [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
- [Maven Lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)

### Design Patterns
- [DAO Pattern](https://www.baeldung.com/java-dao-pattern)
- [MVC Pattern](https://www.geeksforgeeks.org/mvc-design-pattern/)

---

## 📞 Support

### Team Communication

- **Primary:** WhatsApp Group
- **Code Reviews:** GitHub Pull Requests
- **Documentation:** Google Drive (shared folder)
- **Meetings:** Weekly (TBD)

### Getting Help

1. **Check this README first**
2. **Search in GitHub Issues**
3. **Ask in team WhatsApp group**
4. **Contact Team Leader:** Jawad Ali Alnatah
5. **Office Hours:** Dr. Rahma Ahmed / Saeed Matar Alshahrani

---

## ⚖️ License

This project is for educational purposes as part of CSC 305: Software Engineering course at Imam Abdulrahman Bin Faisal University.

---

## 🙏 Acknowledgments

- **Course Instructor:** Dr. Rahma Ahmed
- **Project Advisor:** Saeed Matar Alshahrani
- **University:** Imam Abdulrahman Bin Faisal University
- **College:** Computer Science and Information Technology

---

## 📊 Project Statistics

- **Lines of Code:** ~2,500 (and growing)
- **Dependencies:** 15 libraries
- **Team Size:** 6 members
- **Duration:** 16 weeks
- **Technology:** JavaFX, Maven, MySQL/TiDB

---

## 🔄 Version History

- **v1.0.0** (Current) - Week 10
  - Initial project setup
  - Authentication system
  - Basic UI framework

- **v0.1.0** - Week 8
  - Project structure
  - Database schema
  - Initial planning

---

## 📝 Notes for Team Members

### Before You Start Working:

1. ✅ **Read this entire README**
2. ✅ **Set up your development environment**
3. ✅ **Test that you can run the application**
4. ✅ **Create your feature branch**
5. ✅ **Communicate with team before major changes**

### When Adding New Features:

1. **Create a new branch**
2. **Follow the project structure**
3. **Write clean, commented code**
4. **Test thoroughly before committing**
5. **Update documentation**
6. **Create pull request for review**

### Code Style Guidelines:

- **Naming Convention:** camelCase for variables/methods, PascalCase for classes
- **Comments:** Use Javadoc for classes and methods
- **Indentation:** 4 spaces (no tabs)
- **Line Length:** Max 100 characters
- **Imports:** Remove unused imports

---

## 🎯 Success Criteria

By Week 16, our application should:

- ✅ Run without errors
- ✅ Support all user roles (Admin, Manager, Employee, Supplier)
- ✅ Manage inventory with CRUD operations
- ✅ Handle supplier and purchase orders
- ✅ Generate and scan QR codes
- ✅ Track employee attendance
- ✅ Generate reports (Excel/PDF)
- ✅ Have clean, professional UI
- ✅ Include comprehensive documentation
- ✅ Pass all test cases

---

## 🚀 Quick Start (For the Impatient)

```powershell
# 1. Install Maven (as Administrator)
choco install maven -y

# 2. Clone repo
git clone https://github.com/JawadAlnatah/Integrated-Supply-Chain-Management-System-with-QR-Based-Employee-Attendance-Tracking-.git

# 3. Navigate to project
cd Supply-Chain-Management-System-With-QR-Based-Attendance-Employee-Tracking-

# 4. Build
mvn clean install

# 5. Run
mvn javafx:run

# 6. Login
# Username: admin
# Password: password123
```

---

**Last Updated:** November 13, 2025  
**Project Status:** 🟢 Active Development  
**Current Phase:** Week 10 - Core Development

---

*Made with ❤️ by Team 6 - CSC 305*
