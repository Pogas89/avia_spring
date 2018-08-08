DELETE FROM user;
ALTER TABLE user AUTO_INCREMENT=1000;
DELETE FROM flight;
ALTER TABLE flight AUTO_INCREMENT=1000;
DELETE FROM staff;
ALTER TABLE staff AUTO_INCREMENT=1000;
DELETE FROM department;
ALTER TABLE department AUTO_INCREMENT=1000;
DELETE FROM user_role;
ALTER TABLE user_role AUTO_INCREMENT=1000;
DELETE FROM flight_status;
ALTER TABLE flight_status AUTO_INCREMENT=1000;
-- -----------------------------------------------------
-- Test insert into user_status
-- -----------------------------------------------------
INSERT INTO user_role (role_id, role) VALUES (0,'ROLE_ADMIN');
INSERT INTO user_role (role_id, role) VALUES (1,'ROLE_DISPETCHER');

-- -----------------------------------------------------
-- Test insert into user
-- -----------------------------------------------------
insert into `aviacompany`.`user` (us_login, us_password, us_Fname, us_Lname, us_email, role_id) values
  ('admin', 'admin', 'Dmitry', 'Ivanov','poshta@gmail.com',0);
insert into `aviacompany`.`user` (us_login, us_password, us_Fname, us_Lname, us_email, role_id) values
  ('user1', 'user', 'Ostap', 'Bender','bender@gmail.com',1);

-- -----------------------------------------------------
-- Test insert into department
-- -----------------------------------------------------
INSERT INTO department (dep_id, dep_name) VALUES (0,'PILOT');
INSERT INTO department (dep_id, dep_name) VALUES (1,'NAVIGATOR');
INSERT INTO department (dep_id, dep_name) VALUES (2,'RADIOMAN');
INSERT INTO department (dep_id, dep_name) VALUES (3,'ATTENDANT');
-- -----------------------------------------------------
-- Test insert into staff
-- -----------------------------------------------------
INSERT INTO `aviacompany`.`staff` (`st_Fname`,`st_Lname`,`dep_id`) VALUES ('Alexis','Roy',0),
  ('Kylan','Knapp',2),('Lacota','Rosario',2),('Rylee','Case',3),('Martha','Slater',2),('Vanna','Bruce',2),
  ('Marsden','Meyers',1),('Bruno','Wooten',3),('Nyssa','Waters',3),('Forrest','Riddle',2);


-- -----------------------------------------------------
-- Test insert into flight
-- -----------------------------------------------------
INSERT INTO flight_status (flstat_id, flstat_status) VALUES (0, 'OPENED');
INSERT INTO flight_status (flstat_id, flstat_status) VALUES (1, 'CLOSED');
INSERT INTO flight_status (flstat_id, flstat_status) VALUES (2, 'CANCELED');

-- -----------------------------------------------------
-- Test insert into flight
-- -----------------------------------------------------
INSERT INTO `aviacompany`.`flight` (`fl_name`,`fl_departure`,`fl_destination`,`fl_date`,`fl_time`,`fl_stat_id`)
  VALUES ('79339','Myanmar','Dominica','16.01.18','18:12:45',0),('88170','Norfolk Island','Brazil','17.09.18','00:07:24',0),
    ('57670','Mali','Christmas Island','25.01.18','11:40:42',0),('40655','Zambia','Mauritania','21.07.18','09:48:05',0),
    ('74467','Greenland','Nicaragua','22.10.17','15:44:59',0),('84799','El Salvador','Costa Rica','15.07.17','23:41:28',0);

-- -----------------------------------------------------
-- Test insert into crew
-- -----------------------------------------------------
INSERT INTO `aviacompany`.`crew` (`cr_name`, `user_id`) VALUES ('Crew1',1000),('Crew2',1000);