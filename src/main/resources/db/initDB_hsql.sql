DROP TABLE user_roles IF EXISTS;
DROP TABLE votes IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE meals IF EXISTS;
DROP TABLE menus IF EXISTS;
DROP TABLE restaurants IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE USERS
(
  id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name       VARCHAR(255)            NOT NULL,
  password   VARCHAR(255)            NOT NULL,
  registered TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOLEAN DEFAULT TRUE    NOT NULL,
  CONSTRAINT user_name_idx UNIQUE (name)
);

CREATE TABLE USER_ROLES
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE RESTAURANTS
(
  id   INTEGER      GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  CONSTRAINT restaurant_name_idx UNIQUE (name)
);

CREATE TABLE VOTES
(
  id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ NOT NULL,
  user_id    INTEGER      NOT NULL,
  date       DATE DEFAULT CURRENT_DATE NOT NULL,
  restaurant INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant) REFERENCES restaurants (id) ON DELETE CASCADE,
  CONSTRAINT votes_unique_user_date_idx UNIQUE (user_id, date)
);

CREATE TABLE MENUS
(
  id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  id_rest       INTEGER      NOT NULL,
  add_date      DATE DEFAULT CURRENT_DATE NOT NULL,
  FOREIGN KEY (id_rest) REFERENCES RESTAURANTS (id) ON DELETE CASCADE,
  CONSTRAINT menus_unique_id_datetime_idx UNIQUE (id_rest, add_date)
);

CREATE TABLE MEALS
(
  id      INTEGER      GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ,
  id_menu INTEGER NOT NULL,
  name    VARCHAR(255) NOT NULL,
  price   INT          NOT NULL,
  FOREIGN KEY (id_menu) REFERENCES MENUS (id) ON DELETE CASCADE
);
