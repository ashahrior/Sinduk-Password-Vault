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


## Techs in Action
<ul>
  <li><a href="https://www.oracle.com/java/technologies/javase-jdk11-downloads.html">Java</a></li>
  <li><a href="https://openjfx.io/">JavaFX</a></li>
  <li><a href="https://gluonhq.com/products/scene-builder/">Scenebuilder</a></li>
  <li><a href="https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/">JDBC</a></li>
  <li><a href="https://www.mysql.com/">MySQL</a></li>
</ul>


## Work in Progress
* Adding data encryption-decryption
* Polishing the UI for refined look
* Adding warning alerts
* Onscreen guidance captions upon mouse hover


## Future Plan
* Automatically generate unique random keys for data encryption-decryption for each account holder
* Generating EXCEL/CSV files for records in encrypted format
* EXCEL/CSV file uploading feature with specified format
* Decrypting updated EXCEL, CSV file records via provided keys 
