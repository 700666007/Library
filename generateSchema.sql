create schema library;
use library;

create table genres (
	val varchar(20),
    primary key(val)
);

create table books (
	title varchar(50),
    author varchar(50),
    genre varchar(20),
    path varchar(100),
    primary key(title),
    foreign key (genre) references genres(val)
);

create table users (
	username varchar(20),
    password varchar(45),
    isAdmin tinyint(4),
    lang varchar(3),
    primary key(username)
);

INSERT INTO genres VALUES ('---');
INSERT INTO users VALUES ('admin', 'admin', '1', 'ITA');
