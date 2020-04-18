CREATE TABLE todo
(
 id int NOT NULL,
 name varchar(100) NOT NULL ,
 building varchar(100) NOT NULL,
 assigned varchar(100) NOT NULL,
 completed boolean,
 date date,
 PRIMARY KEY (id)
);
