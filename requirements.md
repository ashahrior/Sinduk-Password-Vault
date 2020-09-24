* java version >= "1.8.0_211"
* mysql connector jar file
* mysql server
* Connect to server with respective credentials
* mysql should contain the following -
  * schema - passbook
  * tables - user_table, web_credentials
  * passbook database creation-
  '''sql
  create database IF NOT EXISTS passbook;
  '''
  
  * user_table creation -
    '''sql
    CREATE TABLE IF NOT EXISTS passbook.user_table(  
    username varchar(25) NOT NULL unique,  
    email_address varchar(45) NOT NULL unique,  
    password varchar(25) NOT NULL,  
    PRIMARY KEY (id)  
    );
    '''
   
   * web_credentials creation-
   '''sql
   create table passbook.web_credentials (
      id int not null auto_increment,
      username varchar(25) not null,
      web_name varchar(25) not null,
      web_address varchar(60) not null,
      web_user_email varchar(45) not null,
      web_password varchar(45) not null,
      primary key (id)
    );
   '''
    
