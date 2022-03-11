create table avgproductperservice
(
    package_id    int             not null
        primary key,
    avgnumber     float default 0 not null,
    numoptservice int   default 0 not null,
    numpackage    int   default 0 not null,
    constraint package_id_fk1
        foreign key (package_id) references package (idPackage)
);

