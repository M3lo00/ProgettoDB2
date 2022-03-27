create table audit
(
    idAudit          int auto_increment
        primary key,
    refUser          int not null,
    refOrder         int not null,

    constraint refUser
        unique (refUser),
    constraint fk_User2
        foreign key (refUser) references user (idUser),
    constraint fk_Order3
            foreign key (refOrder) references order (idOrder)
);



