create table susporder
(
    order_id int not null
        primary key,
    constraint order_fk
        foreign key (order_id) references `order` (idOrder)
);

