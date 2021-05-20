DROP TABLE IF EXISTS cities CASCADE;
DROP TABLE IF EXISTS literature_categories CASCADE;
DROP TABLE IF EXISTS authors CASCADE;
DROP TABLE IF EXISTS publishers CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS lending_books CASCADE;

DROP TYPE IF EXISTS user_role;
DROP TYPE IF EXISTS user_role;

CREATE TYPE user_role AS ENUM ('client', 'librarian', 'administrator');
CREATE TYPE lending_type AS ENUM ('membership', 'hall');

CREATE TABLE IF NOT EXISTS cities (
  id serial PRIMARY KEY,
  postal_code varchar(255) NOT NULL UNIQUE,
  city_name varchar(255) NOT NULL,
  country varchar(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS literature_categories (
  id serial PRIMARY KEY,
  category_name varchar(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS authors (
  id serial PRIMARY KEY,
  name varchar(255) NOT NULL,
  surname varchar(255) NOT NULL,
  pseudonym varchar(255)
);

CREATE TABLE IF NOT EXISTS publishers (
  id serial PRIMARY KEY,
  publisher_name varchar(255) NOT NULL,
  city_id int REFERENCES cities(id)
);

CREATE TABLE IF NOT EXISTS books (
    id int PRIMARY KEY,
    isbn int UNIQUE,
    book_title varchar(255) NOT NULL,
    author_id int REFERENCES authors(id) NOT NULL,
    literature_category_id int REFERENCES literature_categories(id) NOT NULL,
    publisher int REFERENCES publishers(id) NOT NULL,
    year_published date,
    quantity int default 0
);

CREATE TABLE IF NOT EXISTS users (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    surname varchar(255) NOT NULL,
    address varchar(255),
    city_id int REFERENCES cities(id),
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    phone_number varchar(255),
    is_blocked boolean default false,
    role user_role default 'client'
);

CREATE TABLE IF NOT EXISTS lending_books (
    id serial PRIMARY KEY,
    book_id int REFERENCES books(id) NOT NULL,
    user_id int REFERENCES users(id) NOT NULL,
    type lending_type NOT NULL,
    loaned_date timestamp NOT NULL,
    estimated_returned_date timestamp NOT NULL,
    real_returned_date timestamp,
    overdue_fine decimal
);

