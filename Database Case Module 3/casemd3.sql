create database casemd3;

create table category(
idCategory int auto_increment primary key,
nameCategory varchar(255) 
);

create table product(
idProduct int primary key auto_increment,
nameProduct varchar(50),
price int,
description varchar(255),
img varchar(255),
id int,
foreign key (id) references category(idCategory)
);

create table users(
iduser int primary key auto_increment,
userName varchar(50),
pass varchar(50),
emailUser varchar(50),
roll varchar(50),
address varchar(50),
phoneNumber varchar(50)
);