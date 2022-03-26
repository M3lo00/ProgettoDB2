USE dbproj;


CREATE TRIGGER newPackage4
    AFTER INSERT ON package for each row
BEGIN
    INSERT INTO avgproductperservice (package_id)
        VALUE (NEW.idPackage);
END;

drop trigger addOrderAvg;

CREATE TRIGGER addOrderAvg
    AFTER INSERT ON `order` for each row
BEGIN
    DECLARE temporaryChosAdd, x, temporaryChosPack, y  int;
    IF NEW.valid = 1 THEN
        SELECT count(*), o.idOrder INTO temporaryChosAdd, x
        FROM dbproj.order as o
                 INNER JOIN ownoptservice o2 on o.refPack = o2.refPack
        WHERE o.refPack = NEW.refPack
        GROUP BY o.idOrder;

        SELECT count(*), o1.idOrder INTO temporaryChosPack, y
        FROM dbproj.order as o1
                 INNER JOIN ownoptservice o2 on o1.refPack = o2.refPack
        WHERE o1.refPack = NEW.refPack
        GROUP BY o1.idOrder;


        UPDATE avgproductperservice
        SET numoptservice = numoptservice + temporaryChosAdd + temporaryChosPack,
            numpackage = numpackage +1
        WHERE package_id =NEW.refPack;
    END IF;
END;

CREATE TRIGGER upOrderAvg
    AFTER UPDATE ON `order` for each row
BEGIN
    DECLARE temporaryChosAdd, x, temporaryChosPack, y  int;
    IF NEW.valid = 1 THEN
        SELECT count(*), o.idOrder INTO temporaryChosAdd
        FROM dbproj.order as o
                 INNER JOIN ownoptservice o2 on o.refPack = o2.refPack
        WHERE o.refPack = NEW.refPack
        GROUP BY o.idOrder;

        SELECT count(*), o1.idOrder INTO temporaryChosPack
        FROM dbproj.order as o1
                 INNER JOIN ownoptservice o2 on o1.refPack = o2.refPack
        WHERE o1.refPack = NEW.refPack
        GROUP BY o1.idOrder;


        UPDATE avgproductperservice
        SET numoptservice = numoptservice + temporaryChosAdd + temporaryChosPack,
            numpackage = numpackage +1
        WHERE package_id =NEW.refPack;
    END IF;
END;
