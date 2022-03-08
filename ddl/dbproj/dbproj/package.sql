create table package
(
    idPackage   int auto_increment
        primary key,
    name        varchar(45) not null,
    refEmployee int         null,
    sms         int         null,
    minute      int         null,
    mGiga       int         null,
    extraMGiga  float       null,
    extraSMS    float       null,
    fixedPhone  tinyint     null,
    fGiga       int         null,
    extraFGiga  float       null,
    constraint name_UNIQUE
        unique (name),
    constraint fk_EmployeePack
        foreign key (refEmployee) references employee (idEmployee)
);

create index fk_EmployeePack_idx
    on package (refEmployee);

