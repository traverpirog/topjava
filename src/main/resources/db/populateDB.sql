DELETE
FROM meals;
DELETE
FROM user_role;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id)
VALUES ('2024-02-17 09:32:00.000000', 'Завтрак', 900, 100000),
       ('2024-02-17 14:21:00.000000', 'Обед', 750, 100000),
       ('2024-02-17 17:40:00.000000', 'Ужин', 450, 100000),
       ('2024-02-16 10:00:00.000000', 'Завтрак', 500, 100000),
       ('2024-02-16 13:40:00.000000', 'Обед', 500, 100000),
       ('2024-02-16 18:40:00.000000', 'Ужин', 250, 100000);
