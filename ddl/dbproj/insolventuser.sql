create table insolventuser
(
    idInsolventUser int auto_increment
        primary key,
    insolvent_id    int not null,
    constraint uniqueness
        unique (insolvent_id),
    constraint insolvent_fk
        foreign key (insolvent_id) references user (idUser)
);

