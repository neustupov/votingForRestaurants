DELETE FROM user_roles;
DELETE FROM votes;
DELETE FROM users;
DELETE FROM meals;
DELETE FROM menus;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS (name, password) VALUES
  ('User', 'password'),
  ('Admin', 'admin');

INSERT INTO USER_ROLES (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO RESTAURANTS (NAME) VALUES
  ('Russia'),
  ('Ukraine'),
  ('U Kolyana'),
  ('Almaz'),
  ('Fart');

INSERT INTO MENUS (ID_REST, ADD_DATE) VALUES
  (100002, '2015-05-01 08:00:00'),
  (100003, '2015-05-02 09:00:00'),
  (100004, '2015-05-03 10:00:00'),
  (100005, '2015-05-01 08:00:00'),
  (100006, '2015-05-02 07:00:00');

INSERT INTO MEALS (ID_MENU, NAME, PRICE) VALUES
  (100007, 'Завтрак', 500),
  (100008, 'Завтрак', 500),
  (100009, 'Завтрак', 500),
  (100010, 'Завтрак', 500),
  (100011,  'Завтрак', 500);

INSERT INTO VOTES (USER_ID, DATE_TIME, RESTAURANT) VALUES
  (100000, '2015-05-01 10:00:00', 100002),
  (100000, '2015-05-02 11:00:00', 100003),
  (100000, '2015-05-03 12:00:00', 100004),
  (100001, '2015-05-01 10:00:00', 100005),
  (100001, '2015-05-02 11:00:00', 100006),
  (100001, '2015-05-03 12:00:00', 100002);
