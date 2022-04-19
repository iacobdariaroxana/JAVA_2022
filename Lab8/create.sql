DROP TABLE COUNTRIES;
DROP TABLE CONTINENTS;
DROP TABLE CITIES;
CREATE TABLE COUNTRIES(
    id int primary key,
    name varchar2(50),
    code varchar2(10),
    continent int
);

CREATE TABLE CONTINENTS(
    id int primary key, 
    name varchar2(50),
    area number(10,0),
    population number(10,0)
);

CREATE TABLE CITIES(
    id int primary key,
    name varchar2(50),
    country int,
    capital int,
    latitude number(14,10),
    longitude number(14,10)
);