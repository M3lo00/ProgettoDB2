create table insolventuser
(
    insolvent_id int not null
        primary key,
    constraint insolvent_fk
        foreign key (insolvent_id) references user (idUser)
);

