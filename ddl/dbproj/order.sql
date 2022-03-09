create table `order`
(
    idOrder      int auto_increment
        primary key,
    refUser      int                       not null,
    refPack      int                       not null,
    creationDate datetime                  not null,
    startDate    date                      null,
    periodo      int                       not null,
    valid        tinyint unsigned zerofill null,
    totalAmount  int                       not null,
    nMobile      int                       null,
    nFixed       int                       null,
    constraint fk_Package3
        foreign key (refPack) references package (idPackage),
    constraint fk_User
        foreign key (refUser) references user (idUser)
);

create index fk_Package_idx
    on `order` (refPack);

create index fk_User_idx
    on `order` (refUser);

