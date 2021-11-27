CREATE TABLE IF NOT EXISTS users (
                                     id int PRIMARY KEY,
                                     email varchar(255) NOT NULL UNIQUE,
    password text NOT NULL,
    first_name varchar(255),
    last_name varchar(255),
    city varchar(255),
    is_blocked bool DEFAULT false,
    type int
    );

CREATE TABLE IF NOT EXISTS locations (
                                         id int PRIMARY KEY,
                                         note varchar(255) NOT NULL UNIQUE,
    address text NOT NULL,
    street varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    country varchar(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS hotels (
                                      id int PRIMARY KEY,
                                      location_id int REFERENCES locations(id) NOT NULL,
    name varchar(255) NOT NULL,
    description text,
    type int NOT NULL
    );

CREATE TABLE IF NOT EXISTS tours (
                                     id int PRIMARY KEY,
                                     start_location_id int REFERENCES locations(id),
    hotel_id int REFERENCES hotels(id),
    name varchar(255) NOT NULL,
    description text,
    type int,
    price decimal,
    departure_date timestamp WITHOUT TIME ZONE,
    duration bigint,
    active bool DEFAULT false,
    is_on_fire bool DEFAULT false,
    total_places_qty int,
    remaining_places_qty int
    );

CREATE TABLE IF NOT EXISTS orders (
                                      id int PRIMARY KEY,
                                      user_id int REFERENCES users(id) NOT NULL,
    tour_id int REFERENCES tours(id) NOT NULL,
    status int NOT NULL,
    discount float,
    final_price decimal
    )