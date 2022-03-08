create table audit
(
    refUser          int not null,
    refLastRejection int not null,
    primary key (refUser, refLastRejection),
    constraint fk_Payment
        foreign key (refLastRejection) references payment (idPayments),
    constraint fk_User2
        foreign key (refUser) references user (idUser)
);

create index fk_Payment_idx
    on audit (refLastRejection);

