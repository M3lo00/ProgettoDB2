use dbproj;
create table avgproductperservice
(
    idAvgProduct  int          auto_increment,
    package_id    int             not null,
    avgnumber     float default 0 not null,
    numoptservice int   default 0 not null,
    numpackage    int   default 0 not null,
    primary key (idAvgProduct),
    constraint uniqueness
        unique (package_id),
    constraint package_id_fk1
        foreign key (package_id) references package (idPackage)


);

