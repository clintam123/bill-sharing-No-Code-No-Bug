-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bill_sharing
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bill_sharing` ;

-- -----------------------------------------------------
-- Schema bill_sharing
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bill_sharing` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bill_sharing` ;

-- -----------------------------------------------------
-- Table `bill_sharing`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bill_sharing`.`category` ;

CREATE TABLE IF NOT EXISTS `bill_sharing`.`category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(75) NOT NULL,
  `content` TEXT NULL DEFAULT NULL,
  `code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bill_sharing`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bill_sharing`.`user` ;

CREATE TABLE IF NOT EXISTS `bill_sharing`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `registered_at` DATETIME NOT NULL,
  `last_login` DATETIME NULL DEFAULT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password_hash` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bill_sharing`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bill_sharing`.`customer` ;

CREATE TABLE IF NOT EXISTS `bill_sharing`.`customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
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
    REFERENCES `bill_sharing`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bill_sharing`.`vendor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bill_sharing`.`vendor` ;

CREATE TABLE IF NOT EXISTS `bill_sharing`.`vendor` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `intro` TINYTEXT NOT NULL,
  `profile` TEXT NOT NULL,
  `phone` varchar(13) not null,
  `address` VARCHAR(45) NOT NULL,
  `province` VARCHAR(45) NOT NULL,
  `district` varchar(50) NOT NULL,
  `opening_time` time not null,
  `closing_time` time not null,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_vendor_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_vendor_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `bill_sharing`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bill_sharing`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bill_sharing`.`order` ;

CREATE TABLE IF NOT EXISTS `bill_sharing`.`order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `status` SMALLINT NOT NULL DEFAULT '0',
  `shipping` FLOAT NOT NULL DEFAULT '0',
  `discount` FLOAT NOT NULL DEFAULT '0',
  `grand_total` FLOAT NOT NULL DEFAULT '0',
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `vendor_id` BIGINT NOT NULL,
  `customer_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_customer1_idx` (`customer_id` ASC) VISIBLE,
  INDEX `fk_order_vendor1_idx` (`vendor_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `bill_sharing`.`customer` (`id`),
  CONSTRAINT `fk_order_vendor1`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `bill_sharing`.`vendor` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bill_sharing`.`product_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bill_sharing`.`product_group` ;

CREATE TABLE IF NOT EXISTS `bill_sharing`.`product_group` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL,
  `vendor_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_group_vendor1_idx` (`vendor_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_group_vendor1`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `bill_sharing`.`vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bill_sharing`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bill_sharing`.`product` ;

CREATE TABLE IF NOT EXISTS `bill_sharing`.`product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(75) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `sku` VARCHAR(100) NOT NULL,
  `price` FLOAT NOT NULL DEFAULT '0',
  `discount` FLOAT NULL DEFAULT '0',
  `quantity` SMALLINT NOT NULL DEFAULT '0',
  `status` TINYINT(1) NOT NULL DEFAULT '0',
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `category_id` BIGINT NOT NULL,
  `product_group_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_category1_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_product_product_group1_idx` (`product_group_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `bill_sharing`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_product_group1`
    FOREIGN KEY (`product_group_id`)
    REFERENCES `bill_sharing`.`product_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bill_sharing`.`order_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bill_sharing`.`order_item` ;

CREATE TABLE IF NOT EXISTS `bill_sharing`.`order_item` (
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
  INDEX `fk_order_item_customer1_idx` (`customer_id` ASC) VISIBLE,
  INDEX `idx_order_item_order` (`order_id` ASC) VISIBLE,
  INDEX `idx_order_item_product` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_item_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `bill_sharing`.`customer` (`id`),
  CONSTRAINT `fk_order_item_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `bill_sharing`.`order` (`id`),
  CONSTRAINT `fk_order_item_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `bill_sharing`.`product` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bill_sharing`.`product_review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bill_sharing`.`product_review` ;

CREATE TABLE IF NOT EXISTS `bill_sharing`.`product_review` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `rating` SMALLINT NOT NULL DEFAULT '0',
  `created_at` DATETIME NOT NULL,
  `modified_at` DATETIME NULL DEFAULT NULL,
  `content` TEXT NULL DEFAULT NULL,
  `customer_id` BIGINT NOT NULL,
  `product_review_id` INT NOT NULL,
  PRIMARY KEY (`id`, `customer_id`),
  INDEX `fk_product_review_customer1_idx` (`customer_id` ASC) VISIBLE,
  INDEX `idx_review_product` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_review_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `bill_sharing`.`customer` (`id`),
  CONSTRAINT `fk_review_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `bill_sharing`.`product` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
