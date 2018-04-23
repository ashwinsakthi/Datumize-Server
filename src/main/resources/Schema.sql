CREATE TABLE `Product` (
	`Id` INT NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(150) NOT NULL,
	`Descr` VARCHAR(500) NOT NULL,
	`Price` INT NOT NULL,
	`CategoryId` INT NOT NULL,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Category` (
	`CategoryId` INT NOT NULL AUTO_INCREMENT,
	`CategoryName` VARCHAR(100) NOT NULL,
	`CategoryDesc` VARCHAR(500) NOT NULL,
	`DepartmentId` VARCHAR(500) NOT NULL,
	PRIMARY KEY (`CategoryId`)
);

CREATE TABLE `Department` (
	`DepartmentId` INT NOT NULL AUTO_INCREMENT,
	`DepartmentName` VARCHAR(100) NOT NULL,
	`DepartmentDesc` VARCHAR(500) NOT NULL,
	PRIMARY KEY (`DepartmentId`)
);

ALTER TABLE `Product` ADD CONSTRAINT `Product_fk0` FOREIGN KEY (`CategoryId`) REFERENCES `Category`(`CategoryId`);

ALTER TABLE `Category` ADD CONSTRAINT `Category_fk0` FOREIGN KEY (`DepartmentId`) REFERENCES `Department`(`DepartmentId`);
