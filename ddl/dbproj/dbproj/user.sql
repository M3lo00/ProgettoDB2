create table user
(
    idUser    int auto_increment
        primary key,
    email     varchar(45)               not null,
    Username  varchar(45)               not null,
    Password  varchar(45)               not null,
    Insolvent tinyint unsigned zerofill null,
    failedPay int unsigned zerofill     null,
    constraint Username_UNIQUE
        unique (Username),
    constraint email_UNIQUE
        unique (email)
);

