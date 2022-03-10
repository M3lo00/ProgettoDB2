USE dbproj;
create table avgproductperservice
(
    package_id          int not null primary key ,
    avgnumber           float not null default 0,
    numoptservice       int not null default 0,
    numpackage          int not null default 0,
    constraint package_id_fk1
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
    DECLARE temporaryChos, x, z, y  int;
    IF NEW.valid = 1 THEN
        SELECT o.idOrder, count(*) INTO x
        FROM dbproj.order as o
                 INNER JOIN ownoptservice o2 on o.refPack = o2.refPack
        WHERE o.refPack = NEW.refPack
        GROUP BY o.idOrder;

        SELECT o1.idOrder, count(*) INTO y
        FROM dbproj.order as o1
                 INNER JOIN ownoptservice o2 on o1.refPack = o2.refPack
        WHERE o1.refPack = NEW.refPack
        GROUP BY o1.idOrder;

        
        UPDATE avgproductperservice
        SET numoptservice = numoptservice + x + y,
            numpackage = numpackage +1
        WHERE package_id =NEW.refPack;
    END IF;
END;
