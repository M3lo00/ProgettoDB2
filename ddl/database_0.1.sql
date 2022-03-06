USE dbproj;
DROP TABLE IF EXISTS 'audit';
DROP TABLE IF EXISTS 'chosenoptional';
DROP TABLE IF EXISTS 'employee';
DROP TABLE IF EXISTS 'feeperiod';
DROP TABLE IF EXISTS 'optservice';
DROP TABLE IF EXISTS 'order';
DROP TABLE IF EXISTS 'ownoptservice';
DROP TABLE IF EXISTS 'package';
DROP TABLE IF EXISTS 'payment';
DROP TABLE IF EXISTS 'service';
DROP TABLE IF EXISTS 'servpack';
DROP TABLE IF EXISTS 'user';

CREATE TABLE 'user' (
    'idUser' 			int 		NOT NULL AUTO_INCREMENT,
    'email' 			varchar(45) NOT NULL,
    'Username' 			varchar(45) NOT NULL,
    'Password' 			varchar(45) NOT NULL,
    'Insolvent' 		tinyint(3) 	unsigned DEFAULT '0' NOT NULL,
    'failedPay' 		int(10) 	unsigned DEFAULT '0' NOT NULL,

    PRIMARY KEY ('idUser'),
    UNIQUE 'Username_UNIQUE' ('Username'),
    UNIQUE 'email_UNIQUE' ('email')
);

CREATE TABLE 'employee' (
    'idEmployee' 		int NOT NULL AUTO_INCREMENT,
    'email' 			varchar(45) NOT NULL,
    'password' 			varchar(45) NOT NULL,

    PRIMARY KEY ('idEmployee'),
    UNIQUE KEY 'email_UNIQUE' ('email')
);

CREATE TABLE 'service' (
    'idService' 		int NOT NULL AUTO_INCREMENT,
    'Type' 				varchar(45) NOT NULL,

    PRIMARY KEY ('idService')
);

CREATE TABLE 'package' (
    'idPackage'         int NOT NULL AUTO_INCREMENT,
    'name'              varchar(45) NOT NULL,
    'refEmployee'       int DEFAULT NULL, /*forse sarebbe meglio mettere un employee 0 che crea i pacchetti "iniziali"*/

    PRIMARY KEY ('idPackage'),
    UNIQUE KEY 'name_UNIQUE' ('name'),
    /*KEY 'fk_EmployeePack_idx' ('refEmployee'),*/
    CONSTRAINT 'fk_EmployeePack'
        FOREIGN KEY ('refEmployee') REFERENCES 'employee' ('idEmployee')
);

CREATE TABLE 'order' (
     'idOrder' 			int NOT NULL AUTO_INCREMENT,
     'refUser' 			int NOT NULL,
     'refPack' 			int NOT NULL,
     'period' 			int NOT NULL,
     'creationDate' 	datetime NOT NULL,
     'startDate' 		date DEFAULT NULL,
     'valid' 			tinyint(3) unsigned DEFAULT '0' NOT NULL,
     'totalAmount' 		int NOT NULL,

     PRIMARY KEY ('idOrder'),
     CONSTRAINT 'fk_Package3'
         FOREIGN KEY ('refPack') REFERENCES 'package' ('idPackage'),
     CONSTRAINT 'fk_User'
         FOREIGN KEY ('refUser') REFERENCES 'user' ('idUser')
);

CREATE TABLE 'payment' (
    'idPayments' 			int NOT NULL,
    'refOrder' 			    int NOT NULL,
    'refUser' 			    int NOT NULL,
    'status' 				tinyint DEFAULT '0' NOT NULL,
    'payTime' 			    datetime NOT NULL,

    PRIMARY KEY ('idPayments'),
    CONSTRAINT 'fk_Order1'
       FOREIGN KEY ('refOrder') REFERENCES 'order' ('idOrder'),
    CONSTRAINT 'fk_User1'
       FOREIGN KEY ('refUser') REFERENCES 'user' ('idUser')
);

CREATE TABLE 'audit' (
    'refUser' 			int NOT NULL,
    'refLastRejection' 	int NOT NULL,

    PRIMARY KEY ('refUser','refLastRejection'),
    CONSTRAINT 'fk_Payment'
        FOREIGN KEY ('refLastRejection') REFERENCES 'payment' ('idPayments'),
    CONSTRAINT 'fk_User2'
        FOREIGN KEY ('refUser') REFERENCES 'user' ('idUser')
);

CREATE TABLE 'optservice' (
      'idOptService' 		int NOT NULL,
      'name' 				varchar(45) NOT NULL,
      'monthly' 			int NOT NULL,
      'refEmployee' 		int DEFAULT NULL,

      PRIMARY KEY ('idOptService'),
      UNIQUE KEY 'Name_UNIQUE' ('name'),
    /*KEY 'fk_Employee_idx' ('refEmployee'), same as before*/
      CONSTRAINT 'fk_Employee'
          FOREIGN KEY ('refEmployee') REFERENCES 'employee' ('idEmployee')
);

CREATE TABLE 'chosenoptional' (
        'refOrder' 			int NOT NULL,
        'refOptService' 		int NOT NULL,

        PRIMARY KEY ('refOrder','refOptService'),
        /* KEY 'fk_Optional_idx' ('refOptService'),  these are indices which may or may not be needed*/
        CONSTRAINT 'fk_Optional'
            FOREIGN KEY ('refOptService') REFERENCES 'optservice' ('idOptService'),
        CONSTRAINT 'fk_Order'
            FOREIGN KEY ('refOrder') REFERENCES 'order' ('idOrder')
);


CREATE TABLE 'feeperiod' (
  'refPackage' 			int NOT NULL,
  'period' 				int NOT NULL,
  'fee'					int DEFAULT NULL,
  
  PRIMARY KEY ('refPackage','period'),
  CONSTRAINT 'fk_Package2'
	FOREIGN KEY ('refPackage') REFERENCES 'package' ('idPackage')
);

CREATE TABLE 'ownoptservice' (
  'refServPack' 		int NOT NULL,
  'refOptService' 		int NOT NULL,
  
  PRIMARY KEY ('refServPack','refOptService'),
  CONSTRAINT 'fk_OptService'
	FOREIGN KEY ('refOptService') REFERENCES 'optservice' ('idOptService'),
  CONSTRAINT 'fk_Package'
	FOREIGN KEY ('refServPack') REFERENCES 'package' ('idPackage')
);

CREATE TABLE 'servpack' (
  'refService' 			int NOT NULL,
  'refPackage' 			int NOT NULL,
  'sms' 				int DEFAULT NULL,
  'minute' 				int DEFAULT NULL,
  'giga' 				int DEFAULT NULL,
  'extraGigaFee' 		int DEFAULT NULL,
  'ServPackcol' 		float DEFAULT NULL,
  
  PRIMARY KEY ('refService','refPackage'),
  CONSTRAINT 'fk_Package1'
	FOREIGN KEY ('refPackage') REFERENCES 'package' ('idPackage'),
  CONSTRAINT 'fk_Service'
	FOREIGN KEY ('refService') REFERENCES 'service' ('idService')
);


