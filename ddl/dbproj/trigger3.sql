#Total value of sales per package with and without the optional products.

create table packagesales
(
    package_id          int not null primary key ,
    justPack            int not null default 0,
    withOpt             int not null default 0,
    constraint package_id_fk
        foreign key (package_id) references package (idPackage)
);

#questo trigger si potrebbe integrare con quello per le prime 2 query
CREATE TRIGGER newPackage1
    AFTER INSERT ON package for each row
    BEGIN
            INSERT INTO packagesales (package_id)
                VALUE (NEW.idPackage);
    end;

CREATE TRIGGER newOrderTotalValue
    AFTER INSERT ON `order` for each row
BEGIN
    IF NEW.valid = 1 THEN
        UPDATE packagesales
        SET withOpt = withOpt + New.totalAmount,
            justPack = justPack + ( SELECT f.fee
                                    FROM feeperiod f
                                    WHERE f.refPackage=NEW.refPack and f.periodo=NEW.periodo)
        WHERE package_id =NEW.refPack;
    END IF;
end;

CREATE TRIGGER upOrderTotalValue
    AFTER UPDATE ON `order` for each row
BEGIN
    IF NEW.valid = 1 THEN
        UPDATE packagesales
        SET withOpt = withOpt + New.totalAmount,
            justPack = justPack + ( SELECT f.fee
                                    FROM feeperiod f
                                    WHERE f.refPackage=NEW.refPack and f.periodo=NEW.periodo)
        WHERE package_id =NEW.refPack;
    END IF;
end;