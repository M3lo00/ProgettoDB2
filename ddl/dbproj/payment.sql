create table payment
(
    idPayments int      not null
        primary key,
    refOrder   int      not null,
    refUser    int      not null,
    status     tinyint  not null,
    payTime    datetime not null,
    constraint fk_Order1
        foreign key (refOrder) references `order` (idOrder),
    constraint fk_User1
        foreign key (refUser) references user (idUser)
);

create index fk_Order_idx
    on payment (refOrder);

create index fk_User_idx
    on payment (refUser);

