use dbproj;
create table user
(
    idUser    int auto_increment
        primary key,
    email     varchar(45)               not null,
    username  varchar(45)               not null,
    password  varchar(45)               not null,
    insolvent tinyint unsigned default 0 null,
    failedPay int unsigned default 0    null,
    constraint username_UNIQUE
        unique (username),
    constraint email_UNIQUE
        unique (email)
);

