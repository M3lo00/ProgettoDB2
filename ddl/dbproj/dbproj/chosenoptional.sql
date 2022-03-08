create table chosenoptional
(
    refOrder      int not null,
    refOptService int not null,
    primary key (refOrder, refOptService),
    constraint fk_Optional
        foreign key (refOptService) references optservice (idOptService),
    constraint fk_Order
        foreign key (refOrder) references `order` (idOrder)
);

create index fk_Optional_idx
    on chosenoptional (refOptService);

