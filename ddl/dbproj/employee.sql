create table employee
(
    idEmployee int auto_increment
        primary key,
    email      varchar(45) not null,
    password   varchar(45) not null,
    constraint email_UNIQUE
        unique (email)
);

