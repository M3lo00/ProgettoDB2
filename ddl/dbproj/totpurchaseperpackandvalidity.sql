create table totpurchaseperpackandvalidity
(
    idTotalPurchase int          auto_increment,
    package_id      int           not null,
    periodo         int           not null,
    totalPurchases  int default 0 not null,
    primary key (idTotalPurchase),
    constraint uniqueness
        unique (package_id, periodo),
    constraint numberOfTotalPurchasesPerPackage_fk0
        foreign key (package_id) references package (idPackage)
);



