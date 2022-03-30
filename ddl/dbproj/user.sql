create table user
(
    idUser    int auto_increment
        primary key,
    email     varchar(45)               not null,
    username  varchar(45)               not null,
    password  varchar(45)               not null,
    insolvent tinyint unsigned zerofill null,
    failedPay int unsigned zerofill     null,
    constraint Username_UNIQUE
        unique (username),
    constraint email_UNIQUE
        unique (email)
);

