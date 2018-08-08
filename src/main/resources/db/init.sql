
-- -----------------------------------------------------
-- Schema aviacompany
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `aviacompany`;
CREATE DATABASE IF NOT EXISTS `aviacompany` DEFAULT CHARACTER SET utf8 ;
USE `aviacompany` ;
-- -----------------------------------------------------
-- Table `aviacompany`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`department` (
  `dep_id` INT NOT NULL,
  `dep_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`dep_id`),
  UNIQUE INDEX `dep_name_UNIQUE` (`dep_name` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`staff`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `aviacompany`.`staff` (
  `st_id` INT NOT NULL AUTO_INCREMENT,
  `st_Fname` VARCHAR(45) NOT NULL,
  `st_Lname` VARCHAR(45) NOT NULL,
  `dep_id` INT NOT NULL,
  PRIMARY KEY (`st_id`),
  INDEX `dep_id_idx` (`dep_id` ASC),
  CONSTRAINT `dep_id`
  FOREIGN KEY (`dep_id`)
  REFERENCES `aviacompany`.`department` (`dep_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`user_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`user_role` (
  `role_id` INT NOT NULL,
  `role` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `us_login` VARCHAR(30) NOT NULL,
  `us_password` VARCHAR(30) NOT NULL,
  `us_Fname` VARCHAR(45) NOT NULL,
  `us_Lname` VARCHAR(45) NOT NULL,
  `us_email` VARCHAR(20) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `login_UNIQUE` (`us_login` ASC),
  INDEX `status_id_idx` (`role_id` ASC),
  CONSTRAINT `role_id`
  FOREIGN KEY (`role_id`)
  REFERENCES `aviacompany`.`user_role` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`crew`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `aviacompany`.`crew` (
  `cr_id` INT NOT NULL AUTO_INCREMENT,
  `cr_name` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`cr_id`),
  UNIQUE INDEX `name_UNIQUE` (`cr_name` ASC),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `aviacompany`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`flight_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`flight_status` (
  `flstat_id` INT NOT NULL,
  `flstat_status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`flstat_id`),
  UNIQUE INDEX `flstat_status_UNIQUE` (`flstat_status` ASC))
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`flight`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `aviacompany`.`flight` (
  `fl_id` INT NOT NULL AUTO_INCREMENT,
  `fl_name` VARCHAR(45) NOT NULL,
  `fl_departure` VARCHAR(45) NOT NULL,
  `fl_destination` VARCHAR(45) NOT NULL,
  `fl_date` DATE NOT NULL,
  `fl_time` TIME NOT NULL,
  `fl_stat_id` INT NOT NULL,
  `crew_id` INT NULL,
  PRIMARY KEY (`fl_id`),
  INDEX `fl_stat_id_idx` (`fl_stat_id` ASC),
  INDEX `crew_id_idx` (`crew_id` ASC),
  CONSTRAINT `fl_stat_id`
  FOREIGN KEY (`fl_stat_id`)
  REFERENCES `aviacompany`.`flight_status` (`flstat_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `crew_id`
  FOREIGN KEY (`crew_id`)
  REFERENCES `aviacompany`.`crew` (`cr_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`crew_staff`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `aviacompany`.`crew_staff` (
  `cr_id` INT NOT NULL,
  `st_id` INT NOT NULL,
  INDEX `cr_id_idx` (`cr_id` ASC),
  INDEX `st_id_idx` (`st_id` ASC),
  CONSTRAINT `cr_id`
  FOREIGN KEY (`cr_id`)
  REFERENCES `aviacompany`.`crew` (`cr_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `st_id`
  FOREIGN KEY (`st_id`)
  REFERENCES `aviacompany`.`staff` (`st_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
  ENGINE = InnoDB;
