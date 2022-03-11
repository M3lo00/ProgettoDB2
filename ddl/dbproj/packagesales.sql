create table packagesales
(
    package_id int           not null
        primary key,
    justPack   int default 0 not null,
    withOpt    int default 0 not null,
    constraint package_id_fk
        foreign key (package_id) references package (idPackage)
);

