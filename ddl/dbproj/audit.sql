create table audit
(
    idAudit          int auto_increment
        primary key,
    refUser          int not null,
    refLastRejection int not null,
    constraint refUser
        unique (refUser),
    constraint fk_Payment
        foreign key (refLastRejection) references payment (idPayments),
    constraint fk_User2
        foreign key (refUser) references user (idUser)
);

create index fk_Payment_idx
    on audit (refLastRejection);

