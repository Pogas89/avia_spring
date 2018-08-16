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

CREATE TABLE IF NOT EXISTS `aviacompany`.`staffs` (
  `id`    INT         NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `department` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `staffs_id_idx` (`id` ASC)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`users` (
  `id`     INT         NOT NULL AUTO_INCREMENT,
  `email`    VARCHAR(20) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  `firstname`    VARCHAR(45) NOT NULL,
  `lastname`    VARCHAR(45) NOT NULL,
  `enabled`      TINYINT DEFAULT TRUE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `user_id_idx` (`id` ASC)
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`user_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.roles (
  `user_id` INT         NOT NULL,
  `role`    VARCHAR(20) NOT NULL,
  CONSTRAINT `user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `aviacompany`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`crew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`crews` (
  `id`   INT         NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `user_id` INT         NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `user_id_idx2` (`user_id` ASC),
  CONSTRAINT `user_id2`
  FOREIGN KEY (`user_id`)
  REFERENCES `aviacompany`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `aviacompany`.`flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviacompany`.`flights` (
  `id`          INT         NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45) NOT NULL,
  `departure`   VARCHAR(45) NOT NULL,
  `destination` VARCHAR(45) NOT NULL,
  `datetime`    TIMESTAMP   NOT NULL,
  `status` TINYINT NOT NULL,
  `crew_id`        INT         NULL,
  PRIMARY KEY (`id`),
  INDEX `fl_id_idx` (`id` ASC),
  INDEX `crew_id_idx` (`crew_id` ASC),
  CONSTRAINT `crew_id`
  FOREIGN KEY (`crew_id`)
  REFERENCES `aviacompany`.`crews` (`id`)
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
  REFERENCES `aviacompany`.`crews` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `st_id`
  FOREIGN KEY (`st_id`)
  REFERENCES `aviacompany`.`staffs` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB;
