-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema product_management
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `product_management` ;

-- -----------------------------------------------------
-- Schema product_management
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `product_management` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
-- -----------------------------------------------------
-- Schema shop
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `shop` ;

-- -----------------------------------------------------
-- Schema shop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `shop`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shop`.`user` ;

CREATE TABLE IF NOT EXISTS `shop`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `passwordHash` VARCHAR(32) NOT NULL,
  `registered_at` DATETIME NOT NULL,
  `last_login` DATETIME NULL DEFAULT NULL,
  `role_id` BIGINT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `mydb`.`vendor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`vendor` ;

CREATE TABLE IF NOT EXISTS `mydb`.`vendor` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `intro` INT NOT NULL,
  `profile` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `province` VARCHAR(45) NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_vendor_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_vendor_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `shop`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`customer` ;

CREATE TABLE IF NOT EXISTS `mydb`.`customer` (
  `id` BIGINT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `user_id` BIGINT NOT NULL,
  `admin` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_customer_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_customer_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `shop`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `product_management` ;

-- -----------------------------------------------------
-- Table `product_management`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_management`.`category` ;

CREATE TABLE IF NOT EXISTS `product_management`.`category` (
  `id` MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `code` VARCHAR(10) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `status` TINYINT NULL DEFAULT NULL,
  `description` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code` (`code` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 103
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `product_management`.`district`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_management`.`district` ;

CREATE TABLE IF NOT EXISTS `product_management`.`district` (
  `id` MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(10) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `name` VARCHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code` (`code` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `product_management`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_management`.`product` ;

CREATE TABLE IF NOT EXISTS `product_management`.`product` (
  `id` MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  `sku` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `status` TINYINT NULL DEFAULT NULL,
  `description` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `category_id` MEDIUMINT UNSIGNED NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT NULL,
  `modified_at` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `sku` (`sku` ASC) VISIBLE,
  INDEX `category_id` (`category_id` ASC) VISIBLE,
  CONSTRAINT `product_ibfk_1`
    FOREIGN KEY (`category_id`)
    REFERENCES `product_management`.`category` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1018
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `product_management`.`province`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_management`.`province` ;

CREATE TABLE IF NOT EXISTS `product_management`.`province` (
  `id` MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(10) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `name` VARCHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code` (`code` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `product_management`.`warehouse`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_management`.`warehouse` ;

CREATE TABLE IF NOT EXISTS `product_management`.`warehouse` (
  `id` MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `address` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `status` TINYINT NULL DEFAULT NULL,
  `province_id` MEDIUMINT UNSIGNED NULL DEFAULT NULL,
  `district_id` MEDIUMINT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `province_id` (`province_id` ASC) VISIBLE,
  INDEX `district_id` (`district_id` ASC) VISIBLE,
  CONSTRAINT `warehouse_ibfk_1`
    FOREIGN KEY (`province_id`)
    REFERENCES `product_management`.`province` (`id`),
  CONSTRAINT `warehouse_ibfk_2`
    FOREIGN KEY (`district_id`)
    REFERENCES `product_management`.`district` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 101
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `product_management`.`warehouse_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_management`.`warehouse_product` ;

CREATE TABLE IF NOT EXISTS `product_management`.`warehouse_product` (
  `id` MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` MEDIUMINT UNSIGNED NULL DEFAULT NULL,
  `warehouse_id` MEDIUMINT UNSIGNED NULL DEFAULT NULL,
  `inventory` MEDIUMINT NULL DEFAULT NULL,
  `total_import` MEDIUMINT UNSIGNED NULL DEFAULT NULL,
  `total_export` MEDIUMINT UNSIGNED NULL DEFAULT NULL,
  `start_date` DATETIME NULL DEFAULT NULL,
  `expired_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `product_id` (`product_id` ASC) VISIBLE,
  INDEX `warehouse_id` (`warehouse_id` ASC) VISIBLE,
  CONSTRAINT `warehouse_product_ibfk_1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product_management`.`product` (`id`),
  CONSTRAINT `warehouse_product_ibfk_2`
    FOREIGN KEY (`warehouse_id`)
    REFERENCES `product_management`.`warehouse` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 201
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;

USE `shop` ;

-- -----------------------------------------------------
-- Table `shop`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shop`.`category` ;

CREATE TABLE IF NOT EXISTS `shop`.`category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(75) NOT NULL,
  `content` TEXT NULL DEFAULT NULL,
  `code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `shop`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shop`.`order` ;

CREATE TABLE IF NOT EXISTS `shop`.`order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `session_id` VARCHAR(100) NOT NULL,
  `token` VARCHAR(100) NOT NULL,
  `status` SMALLINT NOT NULL DEFAULT '0',
  `shipping` FLOAT NOT NULL DEFAULT '0',
  `total` FLOAT NOT NULL DEFAULT '0',
  `discount` FLOAT NOT NULL DEFAULT '0',
  `grand_total` FLOAT NOT NULL DEFAULT '0',
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `vendor_id` BIGINT NOT NULL,
  `customer_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `vendor_id`, `customer_id`),
  INDEX `fk_order_vendor1_idx` (`vendor_id` ASC) VISIBLE,
  INDEX `fk_order_customer1_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_vendor1`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `mydb`.`vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `shop`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shop`.`product` ;

CREATE TABLE IF NOT EXISTS `shop`.`product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(75) NOT NULL,
  `summary` TINYTEXT NULL DEFAULT NULL,
  `type` SMALLINT NOT NULL DEFAULT '0',
  `sku` VARCHAR(100) NOT NULL,
  `price` FLOAT NOT NULL DEFAULT '0',
  `discount` FLOAT NOT NULL DEFAULT '0',
  `quantity` SMALLINT NOT NULL DEFAULT '0',
  `shop` TINYINT(1) NOT NULL DEFAULT '0',
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `published_at` DATETIME NULL DEFAULT NULL,
  `vendor_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_vendor1_idx` (`vendor_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_vendor1`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `mydb`.`vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `shop`.`order_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shop`.`order_item` ;

CREATE TABLE IF NOT EXISTS `shop`.`order_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT NOT NULL,
  `order_id` BIGINT NOT NULL,
  `quantity` SMALLINT NOT NULL DEFAULT '0',
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `content` TEXT NULL DEFAULT NULL,
  `customer_id` BIGINT NOT NULL,
  `total` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_order_item_product` (`product_id` ASC) VISIBLE,
  INDEX `idx_order_item_order` (`order_id` ASC) VISIBLE,
  INDEX `fk_order_item_customer1_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_item_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `shop`.`order` (`id`),
  CONSTRAINT `fk_order_item_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `shop`.`product` (`id`),
  CONSTRAINT `fk_order_item_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `shop`.`product_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shop`.`product_category` ;

CREATE TABLE IF NOT EXISTS `shop`.`product_category` (
  `product_id` BIGINT NOT NULL,
  `category_id` BIGINT NOT NULL,
  PRIMARY KEY (`product_id`, `category_id`),
  INDEX `idx_pc_category` (`category_id` ASC) VISIBLE,
  INDEX `idx_pc_product` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_pc_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `shop`.`category` (`id`),
  CONSTRAINT `fk_pc_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `shop`.`product` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `shop`.`product_review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shop`.`product_review` ;

CREATE TABLE IF NOT EXISTS `shop`.`product_review` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `rating` SMALLINT NOT NULL DEFAULT '0',
  `created_at` DATETIME NOT NULL,
  `published_at` DATETIME NULL DEFAULT NULL,
  `content` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_review_product` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_review_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `shop`.`product` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
