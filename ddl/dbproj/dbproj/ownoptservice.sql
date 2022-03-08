create table ownoptservice
(
    refServPack   int not null,
    refOptService int not null,
    primary key (refServPack, refOptService),
    constraint fk_OptService
        foreign key (refOptService) references optservice (idOptService),
    constraint fk_Package
        foreign key (refServPack) references package (idPackage)
);

create index fk_OptService_idx
    on ownoptservice (refOptService);

