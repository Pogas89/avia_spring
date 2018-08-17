TRUNCATE TABLE crew_staff RESTART IDENTITY CASCADE;
TRUNCATE TABLE users RESTART IDENTITY CASCADE;
TRUNCATE TABLE flights RESTART IDENTITY CASCADE;
TRUNCATE TABLE staffs RESTART IDENTITY CASCADE;
TRUNCATE TABLE roles RESTART IDENTITY CASCADE;
TRUNCATE TABLE crews RESTART IDENTITY CASCADE;

-- -----------------------------------------------------
-- Test insert into users
-- -----------------------------------------------------
insert into users (email, password, firstname, lastname) values
  ('admin@gmail.com', 'admin', 'Dmitry', 'Ivanov'),
  ('user@gmail.com', 'user', 'Ostap', 'Bender');

-- -----------------------------------------------------
-- Test insert into roles
-- -----------------------------------------------------
INSERT INTO roles (user_id, role) VALUES (1,'ROLE_ADMIN'),(2,'ROLE_DISPETCHER');



-- -----------------------------------------------------
-- Test insert into staffs
-- -----------------------------------------------------
INSERT INTO staffs (firstname, lastname, department) VALUES ('Alexis','Roy',0),('Kylan','Knapp',2);

-- -----------------------------------------------------
-- Test insert into flights
-- -----------------------------------------------------
INSERT INTO flights (name,departure,destination,datetime,status)
  VALUES ('79339','Myanmar','Dominica','2016-01-16 18:12:45',0),('88170','Norfolk Island','Brazil','2018-09-17 00:07:24',0);

-- -----------------------------------------------------
-- Test insert into crews
-- -----------------------------------------------------
INSERT INTO crews (name, user_id) VALUES ('Crew1',1),('Crew2',1);