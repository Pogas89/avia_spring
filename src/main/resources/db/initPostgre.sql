DROP TABLE IF EXISTS crew_staff CASCADE;
DROP TABLE IF EXISTS staffs CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS crews CASCADE;
DROP TABLE IF EXISTS flights CASCADE;

-- -----------------------------------------------------
-- Table staffs
-- -----------------------------------------------------

CREATE TABLE staffs (
  id    bigserial PRIMARY KEY NOT NULL,
  firstname VARCHAR(45) NOT NULL,
  lastname VARCHAR(45) NOT NULL,
  department smallint NOT NULL
);

-- -----------------------------------------------------
-- Table users
-- -----------------------------------------------------
CREATE TABLE users (
  id     bigserial PRIMARY KEY NOT NULL,
  email    VARCHAR(20) NOT NULL,
  password VARCHAR(30) NOT NULL,
  firstname    VARCHAR(45) NOT NULL,
  lastname    VARCHAR(45) NOT NULL,
  enabled      boolean DEFAULT TRUE NOT NULL
);
CREATE UNIQUE INDEX email_UNIQUE on users (email);

-- -----------------------------------------------------
-- Table roles
-- -----------------------------------------------------
CREATE TABLE roles (
  user_id INTEGER NOT NULL,
  role    VARCHAR(20) NOT NULL,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);

-- -----------------------------------------------------
-- Table crews
-- -----------------------------------------------------
CREATE TABLE crews (
  id   bigserial PRIMARY KEY NOT NULL,
  name VARCHAR(45) NOT NULL,
  user_id INTEGER NOT NULL,
  CONSTRAINT user_id2
  FOREIGN KEY (user_id)
  REFERENCES users (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);
CREATE UNIQUE INDEX name_UNIQUE on crews (name);
-- -----------------------------------------------------
-- Table flights
-- -----------------------------------------------------
CREATE TABLE flights (
  id  bigserial PRIMARY KEY NOT NULL,
  name        VARCHAR(45) NOT NULL,
  departure   VARCHAR(45) NOT NULL,
  destination VARCHAR(45) NOT NULL,
  datetime    TIMESTAMP   NOT NULL,
  status      smallint    NOT NULL,
  crew_id     INTEGER         NULL,
  CONSTRAINT crew_id
  FOREIGN KEY (crew_id)
  REFERENCES crews (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);

-- -----------------------------------------------------
-- Table crew_staff
-- -----------------------------------------------------
CREATE TABLE crew_staff (
  cr_id INTEGER NOT NULL,
  st_id INTEGER NOT NULL,
  CONSTRAINT cr_id
  FOREIGN KEY (cr_id)
  REFERENCES crews (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT st_id
  FOREIGN KEY (st_id)
  REFERENCES staffs (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
);