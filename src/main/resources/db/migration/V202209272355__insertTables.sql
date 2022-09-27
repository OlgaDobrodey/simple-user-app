INSERT INTO role(role_name)
VALUES ('ADMIN'),
       ('USER');

INSERT INTO users(last_name, first_name, role_id, email, date_of_birth)
VALUES ('Petrov', 'Petrov', 1, 'Petr@mail.ru', '2011-12-01'),
       ('Ivanov', 'Ivanov', 1, 'Ivan@mail.ru', '2011-12-01'),
       ('Sidorov', 'Sidorov', 2, 'Sidr@mail.ru', '2011-12-01'),
       ('Frolov', 'Frolov', 2, 'Vova@mail.ru', '2011-12-01'),
       ('Kozlov', 'Kozlov', 2, 'Igor@mail.ru', '2011-12-01'),
       ('Kolpokova', 'Kolpokova', 2, 'Sveta@mail.ru', '2011-12-01'),
       ('Kulak', 'Kulak', 2, 'Tanyay@mail.ru', '2011-12-01'),
       ('Dropalo', 'Dropalo', 1, 'Andrey@mail.ru', '2011-12-01'),
       ('Kurgan', 'Kurgan', 1, 'Misha@mail.ru', '2011-12-01'),
       ('Leurdo', 'Leurdo', 1, 'Elena@mail.ru', '2011-12-01');

INSERT INTO book(title, writer)
VALUES ('Red book', 'Author1'),
       ('Green book', 'Author2'),
       ('Blue book', 'Author3'),
       ('Black book', 'Author4');

INSERT INTO users_book(users_id, book_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 1),
       (6, 2),
       (7, 3),
       (8, 4),
       (9, 1),
       (10, 2);

COMMIT




