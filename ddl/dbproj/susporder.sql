create table susporder
(
    idSuspended int     auto_increment,
    order_id int        not null,
    primary key(idSuspended),
    constraint uniqueness
        unique (order_id),
    constraint order_fk
        foreign key (order_id) references order (idOrder)
);

