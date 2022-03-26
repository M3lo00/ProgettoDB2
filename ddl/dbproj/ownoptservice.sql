use dbproj;

create table ownoptservice
(
    refPack       int not null,
    refOptService int not null,
    primary key (refPack, refOptService),
    constraint fk_OptService
        foreign key (refOptService) references optservice (idOptService),
    constraint fk_Package
        foreign key (refPack) references package (idPackage)
);

create index fk_OptService_idx
    on ownoptservice (refOptService);

