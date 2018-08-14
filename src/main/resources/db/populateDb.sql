DELETE FROM user;
ALTER TABLE user AUTO_INCREMENT=1000;
DELETE FROM flight;
ALTER TABLE flight AUTO_INCREMENT=1000;
DELETE FROM staff;
ALTER TABLE staff AUTO_INCREMENT=1000;
DELETE FROM department;
ALTER TABLE department AUTO_INCREMENT=1000;
DELETE FROM role;
ALTER TABLE role AUTO_INCREMENT=1000;
DELETE FROM flight_status;
ALTER TABLE flight_status AUTO_INCREMENT=1000;
DELETE FROM crew;
ALTER TABLE crew AUTO_INCREMENT=1000;

-- -----------------------------------------------------
-- Test insert into user
-- -----------------------------------------------------
insert into `aviacompany`.`user` (us_email, us_password, us_Fname, us_Lname) values
  ('admin@gmail.com', 'admin', 'Dmitry', 'Ivanov'),
  ('user@gmail.com', 'user', 'Ostap', 'Bender');

-- -----------------------------------------------------
-- Test insert into user_status
-- -----------------------------------------------------
INSERT INTO role (user_id, role) VALUES (1000,'ROLE_ADMIN'),(1001,'ROLE_DISPETCHER');



-- -----------------------------------------------------
-- Test insert into staff
-- -----------------------------------------------------
INSERT INTO `aviacompany`.`staff` (`st_Fname`,`st_Lname`) VALUES ('Alexis','Roy'),('Kylan','Knapp');

-- -----------------------------------------------------
-- Test insert into department
-- -----------------------------------------------------
 INSERT INTO department (st_id, dep_name) VALUES (1000,'PILOT'),(1001,'NAVIGATOR');
# INSERT INTO department (dep_id, dep_name) VALUES (2,'RADIOMAN');
# INSERT INTO department (dep_id, dep_name) VALUES (3,'ATTENDANT');

-- -----------------------------------------------------
-- Test insert into flight
-- -----------------------------------------------------
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_datetime`)
  VALUES ('79339','Myanmar','Dominica','2016-01-16 18:12:45'),('88170','Norfolk Island','Brazil','2018-09-17 00:07:24');

-- -----------------------------------------------------
-- Test insert into flight
-- -----------------------------------------------------
 INSERT INTO flight_status (fl_id, fl_status) VALUES (1000, 'OPENED'),(1001, 'CLOSED');
# INSERT INTO flight_status (flstat_id, flstat_status) VALUES (2, 'CANCELED');

-- -----------------------------------------------------
-- Test insert into crew
-- -----------------------------------------------------
INSERT INTO `aviacompany`.`crew` (`cr_name`, `user_id`) VALUES ('Crew1',1000),('Crew2',1000);