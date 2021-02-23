DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES  ('2021-02-01 10:00', '0-Завтрак', 500,  100000),
        ('2021-02-01 13:00', '0-Обед', 1000,  100000),
        ('2021-02-01 20:00', '0-Ужин', 500,  100000),
        ('2021-02-02 00:00', '0-Еда на граничное значение', 100,  100000),
        ('2021-02-02 10:00', '0-Завтрак', 1000,  100000),
        ('2021-02-02 13:00', '0-Обед', 500,  100000),
        ('2021-02-02 20:00', '0-Ужин', 410,  100000),
        ('2021-02-02 10:00', '1-Завтрак', 550,  100001),
        ('2021-02-02 13:00', '1-Обед', 950,  100001),
        ('2021-02-02 20:00', '1-Ужин', 550,  100001),
        ('2021-02-03 00:00', '1-Еда на граничное значение', 150,  100001),
        ('2021-02-03 10:00', '1-Завтрак', 1050,  100001),
        ('2021-02-03 13:00', '1-Обед', 950,  100001),
        ('2021-02-03 20:00', '1-Ужин', 420,  100001);
