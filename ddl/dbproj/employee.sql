create table employee
(
    idEmployee int auto_increment
        primary key,
    username   varchar(45) not null,
    email      varchar(45) not null,
    password   varchar(45) not null,
    constraint email_UNIQUE
        unique (email)
);

