use dbproj;
create table packagesales
(
    package_id          int not null primary key ,
    justPack            int not null default 0,
    withOpt             int not null default 0,
    constraint package_id_fk
        foreign key (package_id) references package (idPackage)
);


CREATE TRIGGER newOrderTotalValue
    AFTER INSERT ON `order` for each row
BEGIN
    DECLARE sconto float default 1;
    IF NEW.periodo=24 THEN
        SET sconto =0.9;
    end if;
    IF NEW.periodo=36 THEN
        SET sconto =0.8;
    end if;
    IF NEW.valid = 1 THEN
        UPDATE packagesales
        SET withOpt = withOpt + New.totalAmount,
            justPack = justPack + ( SELECT p.price12M
                                    FROM package p
                                    WHERE p.idPackage=NEW.refPack) * sconto
        WHERE package_id =NEW.refPack;
    END IF;
end;

CREATE TRIGGER upOrderTotalValue
    AFTER UPDATE ON `order` for each row
BEGIN
    DECLARE sconto float default 1;
    IF NEW.periodo=24 THEN
        SET sconto =0.9;
    end if;
    IF NEW.periodo=36 THEN
        SET sconto =0.8;
    end if;
    IF NEW.valid = 1 THEN
        UPDATE packagesales
        SET withOpt = withOpt + New.totalAmount,
            justPack = justPack + ( SELECT p.price12M
                                    FROM package p
                                    WHERE p.idPackage=NEW.refPack) * sconto
        WHERE package_id =NEW.refPack;
    END IF;
end;