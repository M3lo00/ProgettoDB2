USE dbproj;
create table avgproductperservice
(
    package_id          int not null primary key ,
    avgnumber           float not null default 0,
    numoptservice       int not null default 0,
    numpackage          int not null default 0,
    constraint package_id_fk
        foreign key (package_id) references package (idPackage)
);


CREATE TRIGGER newPackage4
    AFTER INSERT ON package for each row
BEGIN
    INSERT INTO avgproductperservice (package_id)
        VALUE (NEW.idPackage);
END;


CREATE TRIGGER addOrderAvg
    AFTER INSERT ON `order` for each row
BEGIN
    DECLARE temporaryChos  int;
    IF NEW.valid = 1 THEN
        SELECT count(*), o.idOrder INTO temporaryChos
        FROM dbproj.order as o
                 JOIN chosenoptional c on o.idOrder = c.refOrder
                 JOIN ownoptservice o2 on o.refPack = o2.refPack
        WHERE o.refPack = NEW.refPack
        GROUP BY o.idOrder;


        UPDATE avgproductperservice
        SET numoptservice = numoptservice + temporaryChos,
            numpackage = numpackage +1
        WHERE package_id =NEW.refPack;
    END IF;
END;






CREATE TRIGGER upOrderAvg
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