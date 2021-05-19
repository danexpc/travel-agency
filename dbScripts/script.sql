DROP TABLE IF EXISTS cities CASCADE;
DROP TABLE IF EXISTS literature_categories CASCADE;
DROP TABLE IF EXISTS authors CASCADE;
DROP TABLE IF EXISTS publishers CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS memberships CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS loaned_books CASCADE;

DROP TYPE user_role;
DROP TYPE issuance_type;

CREATE TYPE user_role AS ENUM ('student', 'librarian', 'administrator');
CREATE TYPE issuance_type AS ENUM ('membership', 'hall');

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

CREATE TABLE IF NOT EXISTS memberships (
  id serial PRIMARY KEY,
  issued_date date NOT NULL,
  expiry_date date NOT NULL
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
    membership_id int REFERENCES memberships(id) NOT NULL,
    is_blocked boolean default false,
    role user_role default 'student'
);

CREATE TABLE IF NOT EXISTS loaned_books (
    id serial PRIMARY KEY,
    book_id int REFERENCES books(id),
    user_id int REFERENCES users(id),
    type issuance_type,
    date_loaned timestamp,
    estimated_returned_date timestamp,
    real_returned_date timestamp,
    overdue_fine decimal,
    is_returned boolean
)
