-- MySQL Script generated by MySQL Workbench
-- чт, 04-кві-2019 13:31:00 +0300
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `user_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_status` (
  `user_status_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  PRIMARY KEY (`user_status_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(128) NULL,
  `last_name` VARCHAR(128) NULL,
  `email` VARCHAR(128) NULL,
  `password_hash` VARCHAR(128) NULL,
  `salt` VARCHAR(128) NULL,
  `user_status_id` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_users_user_status`
    FOREIGN KEY (`user_status_id`)
    REFERENCES `user_status` (`user_status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_users_user_status_idx` ON `users` (`user_status_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `apartment_classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apartment_classes` (
  `id_apartment_class` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  PRIMARY KEY (`id_apartment_class`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `apartments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `apartments` (
  `apartment_id` INT NOT NULL AUTO_INCREMENT,
  `places_amount` INT NULL,
  `description` MEDIUMTEXT NULL,
  `id_apartment_class` INT NOT NULL,
  PRIMARY KEY (`apartment_id`),
  CONSTRAINT `fk_apartments_apartment_classes1`
    FOREIGN KEY (`id_apartment_class`)
    REFERENCES `apartment_classes` (`id_apartment_class`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_apartments_apartment_classes1_idx` ON `apartments` (`id_apartment_class` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bill` (
  `bill_id` INT NOT NULL AUTO_INCREMENT,
  `amount_due` INT NULL,
  `bank_account_number` VARCHAR(80) NULL,
  PRIMARY KEY (`bill_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orders` (
  `id_order` INT NOT NULL AUTO_INCREMENT,
  `check_in` DATE NULL,
  `check_out` DATE NULL,
  `apartments_apartment_id` INT NOT NULL,
  `users_user_id` INT NOT NULL,
  `bill_bill_id` INT NOT NULL,
  PRIMARY KEY (`id_order`),
  CONSTRAINT `fk_orders_apartments1`
    FOREIGN KEY (`apartments_apartment_id`)
    REFERENCES `apartments` (`apartment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_bill1`
    FOREIGN KEY (`bill_bill_id`)
    REFERENCES `bill` (`bill_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_orders_apartments1_idx` ON `orders` (`apartments_apartment_id` ASC) VISIBLE;

CREATE INDEX `fk_orders_users1_idx` ON `orders` (`users_user_id` ASC) VISIBLE;

CREATE INDEX `fk_orders_bill1_idx` ON `orders` (`bill_bill_id` ASC) VISIBLE;