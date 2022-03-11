create table feeperiod
(
    idFeePeriod int auto_increment
        primary key,
    refPackage  int not null,
    periodo     int not null,
    fee         int null,
    constraint fk_Package1
        foreign key (refPackage) references package (idPackage)
);

