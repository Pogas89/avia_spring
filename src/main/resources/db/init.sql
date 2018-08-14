-- -----------------------------------------------------
-- Schema aviacompany
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `aviacompany`;
CREATE DATABASE IF NOT EXISTS `aviacompany`
  DEFAULT CHARACTER SET utf8;
USE `aviacompany`;
-- -----------------------------------------------------
-- Table `aviacompany`.`staff`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `aviacompany`.`staff` (
  `st_id`    INT         NOT NULL AUTO_INCREMENT,
  `st_Fname` VARCHAR(45) NOT NULL,
  `st_Lname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`st_id`),
  INDEX `st_id_idx` (`st_id` ASC)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`department` (
  `st_id`    INT         NOT NULL,
  `dep_name` VARCHAR(20) NOT NULL,
  CONSTRAINT `st_id_dep`
  FOREIGN KEY (`st_id`)
  REFERENCES `aviacompany`.`staff` (`st_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`user` (
  `user_id`     INT         NOT NULL AUTO_INCREMENT,
  `us_email`    VARCHAR(20) NOT NULL,
  `us_password` VARCHAR(30) NOT NULL,
  `us_Fname`    VARCHAR(45) NOT NULL,
  `us_Lname`    VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email_UNIQUE` (`us_email` ASC),
  INDEX `user_id_idx` (`user_id` ASC)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`user_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.role (
  `user_id` INT         NOT NULL,
  `role`    VARCHAR(20) NOT NULL,
  CONSTRAINT `user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `aviacompany`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`crew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`crew` (
  `cr_id`   INT         NOT NULL AUTO_INCREMENT,
  `cr_name` VARCHAR(45) NOT NULL,
  `user_id` INT         NOT NULL,
  PRIMARY KEY (`cr_id`),
  UNIQUE INDEX `name_UNIQUE` (`cr_name` ASC),
  INDEX `user_id_idx2` (`user_id` ASC),
  CONSTRAINT `user_id2`
  FOREIGN KEY (`user_id`)
  REFERENCES `aviacompany`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`flight` (
  `fl_id`          INT         NOT NULL AUTO_INCREMENT,
  `fl_name`        VARCHAR(45) NOT NULL,
  `fl_departure`   VARCHAR(45) NOT NULL,
  `fl_destination` VARCHAR(45) NOT NULL,
  `fl_datetime`    TIMESTAMP   NOT NULL,
  `crew_id`        INT         NULL,
  PRIMARY KEY (`fl_id`),
  INDEX `fl_id_idx` (`fl_id` ASC),
  INDEX `crew_id_idx` (`crew_id` ASC),
  CONSTRAINT `crew_id`
  FOREIGN KEY (`crew_id`)
  REFERENCES `aviacompany`.`crew` (`cr_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`flight_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`flight_status` (
  `fl_id`     INT         NOT NULL,
  `fl_status` VARCHAR(45) NOT NULL,
  CONSTRAINT `fl_id`
  FOREIGN KEY (`fl_id`)
  REFERENCES `aviacompany`.`flight` (`fl_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`crew_staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`crew_staff` (
  `cr_id` INT NOT NULL,
  `st_id` INT NOT NULL,
  INDEX `cr_id_idx` (`cr_id` ASC),
  INDEX `st_id_idx2` (`st_id` ASC),
  CONSTRAINT `cr_id`
  FOREIGN KEY (`cr_id`)
  REFERENCES `aviacompany`.`crew` (`cr_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `st_id`
  FOREIGN KEY (`st_id`)
  REFERENCES `aviacompany`.`staff` (`st_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB;
