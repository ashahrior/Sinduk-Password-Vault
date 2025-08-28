# <img src="https://github.com/ashahrior/Sinduk-Password-Vault/blob/master/screenshots/lock_logo.png" width="20" height="30"/> Sinduk

**Sinduk** is a minimal JavaFX GUI based desktop application project for password management as it stores and manages your most sensitive information. It saves many different types of information, such as online usernames, passwords, URLs, in encrypted formats inside MySQL database. This application has multiple user authentication system with distinct users having their individual password protected online-credentials vault. This project incorporates with user-friendly database CRUD operations for record management.


## Features List

### SignIN
<img src = "https://github.com/ashahrior/Sinduk-Password-Vault/blob/master/screenshots/sinduk-login.png">

* Only the users having accounts registered within the user-database would be able to gain access inside the application
* Users can login the application using their **username/email** and **password** that they provided during **signup**.
* Multiple user accounts can login to the same application each having their own individual records storage.
* Empty fields will not be granted permission.

### SignUP
<img src="https://github.com/ashahrior/Sinduk-Password-Vault/blob/master/screenshots/sinduk-signup.png">

* To complete registration one must provide **username**, **email address**, **password**.
* Each user account will be unique. Already taken usernames, emails will not be allowed for re-registration.
* Certain format standards for username, email, password are to be maintained while providing user credentials during registration.
* Invalid credentials will be given warning.
* Empty fields will not be granted permission.

### Records
<img src="https://github.com/ashahrior/Sinduk-Password-Vault/blob/master/screenshots/sinduk-home3.png" >

* The records manager page embodies the following dynamic features-
	* Logged in users primary credentials set automatically
	* Count of existing records for the active user for the time
	* **Logout** option
	* View of stored records in tabular format
	* CRUD operations integrated with **ADD**, **UPDATE**, **DELETE** button
	* Dynamically typed search results displayed in table along with the count of similar results found
	* Password visibility controling **Show/Hide** button
	* **Clear** button to reset all fields
	* Mouseclick row selection on table row automatically fills the editable information fields underneath the table 

<img src="https://github.com/ashahrior/Sinduk-Password-Vault/blob/master/screenshots/sinduk-home.png" >

* Any kind of modification through **ADD**, **UPDATE**, **DELETE** button and password visibility operation **Show** button will require account-holders password verification.
* Empty fields will not make any effect.


## Setup and Installation

### Prerequisites
- Java 8 or higher
- MySQL Server
- IntelliJ IDEA (recommended) or any Java IDE

### Dependencies
The project includes the following dependencies in the `resources/` folder:
- `mysql-connector-java-8.0.21.jar` - MySQL database connectivity
- `jfoenix-8.0.10.jar` - Material Design UI components

### Database Setup
1. Install and start MySQL Server
2. Create the required database and tables:

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

3. Update database credentials in `src/database/DBCredentials.java` if needed:
   - Default connection: `localhost:3306`
   - Database: `passbook`
   - Username: `root`
   - Password: `ce123`

### Running the Application
1. Open the project in IntelliJ IDEA
2. Ensure the JFoenix library is configured in project settings
3. Run the `Main.java` class in the `application` package
4. Alternatively, compile and run via command line:
```bash
java -cp "src:resources/mysql-connector-java-8.0.21.jar:resources/jfoenix-8.0.10.jar" application.Main
```

## Architecture Overview

### Package Structure
- `application/` - Main JavaFX application entry point
- `controllers/` - FXML controller classes for UI management
- `account_credentials/` - User account management and data models
- `database/` - Database connectivity and operations
- `FXML/` - JavaFX scene layouts and stylesheets

### Key Design Patterns
- **MVC Pattern**: Separation of FXML views, Controller classes, and Model classes
- **Singleton Pattern**: UserHolder maintains current user session state
- **DAO Pattern**: Database package handles all data access operations

## Technologies Used
<ul>
  <li><a href="https://www.oracle.com/java/technologies/javase-jdk11-downloads.html">Java 8+</a></li>
  <li><a href="https://openjfx.io/">JavaFX</a> - GUI framework</li>
  <li><a href="http://www.jfoenix.com/">JFoenix 8.0.10</a> - Material Design components</li>
  <li><a href="https://gluonhq.com/products/scene-builder/">Scene Builder</a> - FXML visual editor</li>
  <li><a href="https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/">JDBC</a> - Database connectivity</li>
  <li><a href="https://www.mysql.com/">MySQL 8.0+</a> - Database server</li>
</ul>


## Security Considerations

⚠️ **Important Security Notes:**
- Database credentials are currently hardcoded in `DBCredentials.java`
- Passwords are stored in plain text (encryption not yet implemented)
- Consider externalizing database configuration to environment variables

## Work in Progress
* Adding data encryption-decryption for stored credentials
* Polishing the UI for refined look and better user experience  
* Adding comprehensive warning alerts and input validation
* Onscreen guidance captions and tooltips upon mouse hover

## Future Plan
* Automatically generate unique random keys for data encryption-decryption for each account holder
* Export functionality: Generate EXCEL/CSV files for records in encrypted format
* Import functionality: EXCEL/CSV file uploading feature with specified format
* Decrypt and import updated EXCEL/CSV file records via provided keys
* External configuration management for database credentials
* Enhanced security features and audit logging 
