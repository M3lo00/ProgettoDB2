create table optservice
(
    idOptService int         not null
        primary key,
    name         varchar(45) not null,
    monthly      int         not null,
    refEmployee  int         null,
    constraint Name_UNIQUE
        unique (name),
    constraint fk_Employee
        foreign key (refEmployee) references employee (idEmployee)
);

create index fk_Employee_idx
    on optservice (refEmployee);

