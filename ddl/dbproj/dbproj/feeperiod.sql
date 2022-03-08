create table feeperiod
(
    refPackage int not null
        primary key,
    periodo    int not null,
    fee        int null,
    constraint fk_Package2
        foreign key (refPackage) references package (idPackage)
);

