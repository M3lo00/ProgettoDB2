DROP TABLE IF EXISTS `audit`;
DROP TABLE IF EXISTS `chosenoptional`;
DROP TABLE IF EXISTS `employee`;
DROP TABLE IF EXISTS `feeperiod`;
DROP TABLE IF EXISTS `optservice`;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `ownoptservice`;
DROP TABLE IF EXISTS `package`;
DROP TABLE IF EXISTS `payment`;
DROP TABLE IF EXISTS `service`;
DROP TABLE IF EXISTS `servpack`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `audit` (
  `refUser` 			int NOT NULL,
  `refLastRejection` 	int NOT NULL,
  PRIMARY KEY (`refUser`,`refLastRejection`),
  CONSTRAINT `fk_Payment` FOREIGN KEY (`refLastRejection`) REFERENCES `payment` (`idPayments`),
  CONSTRAINT `fk_User2` FOREIGN KEY (`refUser`) REFERENCES `user` (`idUser`)
)

CREATE TABLE `user` (
  `idUser` 				int 		NOT NULL AUTO_INCREMENT,
  `email` 				varchar(45) NOT NULL,
  `Username` 			varchar(45) NOT NULL,
  `Password` 			varchar(45) NOT NULL,
  `Insolvent` 			tinyint(3) 	unsigned DEFAULT 0 NOT NULL,
  `failedPay` 			int(10) 	unsigned DEFAULT 0 NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE `Username_UNIQUE` (`Username`),
  UNIQUE `email_UNIQUE` (`email`)
)