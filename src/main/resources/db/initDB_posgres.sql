DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE GLOBAL_SEQ START 100000;

CREATE TABLE USERS
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE USER_ROLES
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE RESTAURANTS
(
  id   INTEGER      PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR      NOT NULL,
  CONSTRAINT restaurant_name_idx UNIQUE (name)
);

CREATE TABLE VOTES
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq') NOT NULL,
  user_id    INTEGER      NOT NULL,
  date       DATE DEFAULT CURRENT_DATE NOT NULL,
  restaurant INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant) REFERENCES restaurants (id) ON DELETE CASCADE,
  CONSTRAINT votes_unique_user_date_idx UNIQUE (user_id, date)
);

CREATE TABLE MENUS
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  id_rest       INTEGER      NOT NULL,
  add_date      DATE DEFAULT CURRENT_DATE NOT NULL,
  FOREIGN KEY (id_rest) REFERENCES RESTAURANTS (id) ON DELETE CASCADE,
  CONSTRAINT menus_unique_id_datetime_idx UNIQUE (id_rest, add_date)
);

CREATE TABLE MEALS
(
  id      INTEGER      DEFAULT nextval('global_seq'),
  id_menu INTEGER      NOT NULL,
  name    VARCHAR      NOT NULL,
  price   INT          NOT NULL,
  FOREIGN KEY (id_menu) REFERENCES MENUS (id) ON DELETE CASCADE
);