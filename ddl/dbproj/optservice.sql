create table optservice
(
    idOptService int auto_increment
        primary key,
    name         varchar(45) not null,
    monthly      float       not null,
    refEmployee  int         null,
    totSold      float       default 0 not null,
    constraint Name_UNIQUE
        unique (name),
    constraint fk_Employee
        foreign key (refEmployee) references employee (idEmployee)
);

create index fk_Employee_idx
    on optservice (refEmployee);

create index sold
    on optservice (totSold);

