DELETE FROM users;
ALTER TABLE users AUTO_INCREMENT=1000;
DELETE FROM flights;
ALTER TABLE flights AUTO_INCREMENT=1000;
DELETE FROM staffs;
ALTER TABLE staffs AUTO_INCREMENT=1000;
DELETE FROM roles;
ALTER TABLE roles AUTO_INCREMENT=1000;
DELETE FROM crews;
ALTER TABLE crews AUTO_INCREMENT=1000;

-- -----------------------------------------------------
-- Test insert into user
-- -----------------------------------------------------
insert into `aviacompany`.`users` (`email`, `password`, `firstname`, `lastname`) values
  ('admin@gmail.com', 'admin', 'Dmitry', 'Ivanov'),
  ('user@gmail.com', 'user', 'Ostap', 'Bender');

-- -----------------------------------------------------
-- Test insert into user_status
-- -----------------------------------------------------
INSERT INTO `aviacompany`.`roles` (`user_id`, `role`) VALUES (1000,'ROLE_ADMIN'),(1001,'ROLE_DISPETCHER');



-- -----------------------------------------------------
-- Test insert into staff
-- -----------------------------------------------------
INSERT INTO `aviacompany`.`staffs` (`firstname`, `lastname`, `department`) VALUES ('Alexis','Roy',0),('Kylan','Knapp',1);

-- -----------------------------------------------------
-- Test insert into flight
-- -----------------------------------------------------
INSERT INTO `aviacompany`.`flights` (`name`,`departure`,`destination`,`datetime`,`status`)
  VALUES ('79339','Myanmar','Dominica','2016-01-16 18:12:45',0),('88170','Norfolk Island','Brazil','2018-09-17 00:07:24',0);

-- -----------------------------------------------------
-- Test insert into crew
-- -----------------------------------------------------
INSERT INTO `aviacompany`.`crews` (`name`, `user_id`) VALUES ('Crew1',1000),('Crew2',1000);