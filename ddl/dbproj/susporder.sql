create table susporder
(
    idSuspended int auto_increment
        primary key,
    order_id    int not null,
    constraint uniqueness
        unique (order_id),
    constraint order_fk
        foreign key (order_id) references `order` (idOrder)
);

