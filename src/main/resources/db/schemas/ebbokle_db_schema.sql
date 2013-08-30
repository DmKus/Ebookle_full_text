SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
CREATE SCHEMA IF NOT EXISTS `ebookle_db` ;
USE `mydb` ;
USE `ebookle_db` ;

-- -----------------------------------------------------
-- Table `ebookle_db`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebookle_db`.`User` ;

CREATE  TABLE IF NOT EXISTS `ebookle_db`.`User` (
  `id` INT NOT NULL ,
  `login` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `surname` VARCHAR(45) NULL ,
  `isActivated` TINYINT(1)  NOT NULL ,
  `version` INT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebookle_db`.`Book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebookle_db`.`Book` ;

CREATE  TABLE IF NOT EXISTS `ebookle_db`.`Book` (
  `id` INT NOT NULL ,
  `title` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  `User_id` INT NOT NULL ,
  `version` INT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Book_User` (`User_id` ASC) ,
  CONSTRAINT `fk_Book_User`
    FOREIGN KEY (`User_id` )
    REFERENCES `ebookle_db`.`User` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebookle_db`.`Chapter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebookle_db`.`Chapter` ;

CREATE  TABLE IF NOT EXISTS `ebookle_db`.`Chapter` (
  `id` INT NOT NULL ,
  `chapterIndex` INT NOT NULL ,
  `chapterTitle` VARCHAR(45) NOT NULL ,
  `text` TEXT NOT NULL ,
  `Book_id` INT NOT NULL ,
  `version` INT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Chapter_Book1` (`Book_id` ASC) ,
  CONSTRAINT `fk_Chapter_Book1`
    FOREIGN KEY (`Book_id` )
    REFERENCES `ebookle_db`.`Book` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebookle_db`.`Comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebookle_db`.`Comment` ;

CREATE  TABLE IF NOT EXISTS `ebookle_db`.`Comment` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `comment` VARCHAR(500) NOT NULL ,
  `Book_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Comment_Book1` (`Book_id` ASC) ,
  CONSTRAINT `fk_Comment_Book1`
    FOREIGN KEY (`Book_id` )
    REFERENCES `ebookle_db`.`Book` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebookle_db`.`Tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebookle_db`.`Tag` ;

CREATE  TABLE IF NOT EXISTS `ebookle_db`.`Tag` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `tag` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebookle_db`.`Roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebookle_db`.`Roles` ;

CREATE  TABLE IF NOT EXISTS `ebookle_db`.`Roles` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `userId` INT NOT NULL ,
  `role` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `userId_UNIQUE` (`userId` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebookle_db`.`Category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebookle_db`.`Category` ;

CREATE  TABLE IF NOT EXISTS `ebookle_db`.`Category` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `category` VARCHAR(100) NOT NULL ,
  `Book_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Category_Book1` (`Book_id` ASC) ,
  CONSTRAINT `fk_Category_Book1`
    FOREIGN KEY (`Book_id` )
    REFERENCES `ebookle_db`.`Book` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebookle_db`.`Book_has_Tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebookle_db`.`Book_has_Tag` ;

CREATE  TABLE IF NOT EXISTS `ebookle_db`.`Book_has_Tag` (
  `Book_id` INT NOT NULL ,
  `Tag_id` INT NOT NULL ,
  PRIMARY KEY (`Book_id`, `Tag_id`) ,
  INDEX `fk_Book_has_Tag_Book1` (`Book_id` ASC) ,
  INDEX `fk_Book_has_Tag_Tag1` (`Tag_id` ASC) ,
  CONSTRAINT `fk_Book_has_Tag_Book1`
    FOREIGN KEY (`Book_id` )
    REFERENCES `ebookle_db`.`Book` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Book_has_Tag_Tag1`
    FOREIGN KEY (`Tag_id` )
    REFERENCES `ebookle_db`.`Tag` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebookle_db`.`Prefer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebookle_db`.`Prefer` ;

CREATE  TABLE IF NOT EXISTS `ebookle_db`.`Prefer` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `like` TINYINT(1)  NOT NULL ,
  `Book_id` INT NOT NULL ,
  `User_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Prefer_Book1` (`Book_id` ASC) ,
  INDEX `fk_Prefer_User1` (`User_id` ASC) ,
  CONSTRAINT `fk_Prefer_Book1`
    FOREIGN KEY (`Book_id` )
    REFERENCES `ebookle_db`.`Book` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Prefer_User1`
    FOREIGN KEY (`User_id` )
    REFERENCES `ebookle_db`.`User` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebookle_db`.`Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ebookle_db`.`Role` ;

CREATE  TABLE IF NOT EXISTS `ebookle_db`.`Role` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `role` VARCHAR(45) NOT NULL ,
  `User_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Role_User1` (`User_id` ASC) ,
  CONSTRAINT `fk_Role_User1`
    FOREIGN KEY (`User_id` )
    REFERENCES `ebookle_db`.`User` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
