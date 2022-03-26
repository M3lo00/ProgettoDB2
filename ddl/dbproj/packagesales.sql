create table packagesales
(

    idSales    int           auto_increment,
    package_id int           not null,
    justPack   double default 0 not null,
    withOpt    double default 0 not null,
    primary key (idSales),
    constraint uniqueness
        unique (package_id),
    constraint package_id_fk
        foreign key (package_id) references package (idPackage)


);

