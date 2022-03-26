create table insolventuser
(
    idInsolventUser int     auto_increment ,
    insolvent_id    int     not null,
    primary key(idInsolventUser),
    constraint uniqueness
        unique (insolvent_id),
    constraint insolvent_fk
        foreign key (insolvent_id) references user (idUser)
);