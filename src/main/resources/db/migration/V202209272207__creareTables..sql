CREATE TABLE IF NOT EXISTS role
(
    id        SERIAL      NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id),
    CONSTRAINT role_unique UNIQUE (role_name)
);

CREATE TABLE IF NOT EXISTS users
(
    id            SERIAL      NOT NULL,
    first_name    VARCHAR(50) NOT NULL,
    last_name     VARCHAR(50) NOT NULL,
    role_id       INT,
    email         VARCHAR(50) NOT NULL,
    date_of_birth DATE        NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT email_unique UNIQUE (email),
    CONSTRAINT fk_role FOREIGN KEY (role_id)
        REFERENCES role (id)
);

CREATE TABLE IF NOT EXISTS book
(
    id     SERIAL      NOT NULL,
    title  VARCHAR(50) NOT NULL,
    writer VARCHAR(50) NOT NULL,
    CONSTRAINT book_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_book
(
    users_id INT NOT NULL,
    book_id  INT NOT NULL,
    CONSTRAINT users_book_pkey PRIMARY KEY (users_id, book_id),
    CONSTRAINT fk_book_user FOREIGN KEY (book_id)
        REFERENCES book (id),
    CONSTRAINT fk_users_book FOREIGN KEY (users_id)
        REFERENCES users (id)
);

COMMIT