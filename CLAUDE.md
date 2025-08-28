# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Sinduk is a JavaFX-based desktop password vault application that provides secure storage and management of user credentials. The application uses MySQL for data persistence and implements a multi-user authentication system where each user has their own protected credential vault.

## Build and Run Commands

### Prerequisites Setup
- Ensure Java 8+ is installed
- MySQL server must be running
- Database setup required (see Database Setup section)

### Running the Application
```bash
# Compile and run through IntelliJ IDEA or
java -cp "src:resources/mysql-connector-java-8.0.21.jar:resources/jfoenix-8.0.10.jar" application.Main
```

### Database Setup
The application requires a MySQL database named `passbook` with the following tables:

```sql
-- Create database
CREATE DATABASE IF NOT EXISTS passbook;

-- User accounts table
CREATE TABLE IF NOT EXISTS passbook.user_table(  
    username varchar(25) NOT NULL unique,  
    email_address varchar(45) NOT NULL unique,  
    password varchar(25) NOT NULL,  
    PRIMARY KEY (username)  
);

-- Web credentials storage table
CREATE TABLE IF NOT EXISTS passbook.web_credentials (
    id int not null auto_increment,
    username varchar(25) not null,
    web_name varchar(25) not null,
    web_address varchar(60) not null,
    web_user_email varchar(45) not null,
    web_password varchar(45) not null,
    primary key (id)
);
```

## Architecture Overview

### Package Structure
- `application/` - Main JavaFX application entry point
- `controllers/` - FXML controller classes for UI components
  - `LoginController` - Handles login screen logic
  - `SignUPController` - Manages user registration
  - `HomePageController` - Main application interface with CRUD operations
  - `PasswordDialog` - Password verification dialogs
- `account_credentials/` - User account management and data models
  - `Account` - User account data model
  - `AccountAccess` - Login authentication logic
  - `AccountCreation` - User registration logic
  - `AccountTableDataManager/Model` - Table data handling for stored credentials
  - `UserHolder` - Singleton pattern for current user session
- `database/` - Database connectivity and operations
  - `DBConnectionManager` - MySQL connection management
  - `DBCredentials` - Database configuration (hardcoded credentials)
  - `DBAccountAccessor/Creator` - User account database operations
  - `DBTableDataManager` - CRUD operations for credential records

### Key Architecture Patterns
- **MVC Pattern**: FXML views, Controller classes, and Model classes in account_credentials package
- **Singleton Pattern**: UserHolder maintains current logged-in user state
- **DAO Pattern**: Database package handles all data access operations
- **Observer Pattern**: TableView with ObservableList for dynamic UI updates

### Database Configuration
Database credentials are hardcoded in `DBCredentials.java`:
- Host: localhost:3306
- Database: passbook  
- Username: root
- Password: ce123

**Security Note**: Database credentials should be externalized to environment variables or configuration files.

### Authentication Flow
1. Login attempt validates credentials via `AccountAccess.attemptLogin()`
2. Supports both username/email and password combinations
3. Successful login stores user in `UserHolder` singleton
4. All credential operations require password re-verification via `PasswordDialog`

### Data Operations
- **CRUD Operations**: Managed through `AccountTableDataManager` and `DBTableDataManager`
- **Search Functionality**: Dynamic filtering of stored credentials
- **Password Visibility**: Toggle show/hide with password verification
- **Table Selection**: Click-to-populate form fields for editing

### Dependencies
- JavaFX for GUI framework
- JFoenix 8.0.10 for Material Design components  
- MySQL Connector/J 8.0.21 for database connectivity
- MySQL database server

## Development Notes

### Current Limitations
- Database credentials are hardcoded (security risk)
- Passwords stored in plain text (no encryption implemented)
- No data validation on credential length limits
- Limited error handling and user feedback

### Work in Progress
- Data encryption/decryption implementation
- UI polishing and refinements
- Enhanced warning alerts and validation
- Mouse hover guidance tooltips