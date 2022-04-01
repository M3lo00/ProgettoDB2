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

CREATE TRIGGER addOrderAvg
    AFTER INSERT ON `order` for each row
BEGIN
    DECLARE temporaryChosPack int;
    IF NEW.valid = 1 THEN

        SET temporaryChosPack := (SELECT count(*)
                                  FROM ownoptservice o
                                  WHERE o.refPack=NEW.refPack);

        UPDATE avgproductperservice
        SET numoptservice = numoptservice + temporaryChosPack,
            numpackage = numpackage +1
        WHERE package_id =NEW.refPack;
    END IF;
END;

CREATE TRIGGER addChosenOptAvg
    AFTER INSERT ON `chosenoptional` for each row
BEGIN
    UPDATE avgproductperservice
    SET numoptservice = numoptservice + 1
    WHERE package_id =( SELECT o.refPack
                        FROM `order` o
                        WHERE idOrder=NEW.refOrder);
END;

drop trigger upOrderAvg;

CREATE TRIGGER upOrderAvg
    AFTER UPDATE ON `order` for each row
BEGIN
    DECLARE temporaryChosAdd, temporaryChosPack int;
    IF NEW.valid = 1 THEN
        SET temporaryChosAdd := (SELECT count(*)
                                 FROM chosenoptional c
                                 WHERE c.refOrder=NEW.idOrder);

        SET temporaryChosPack := (  SELECT count(*)
                                    FROM ownoptservice o
                                    WHERE o.refPack=NEW.refPack);

        UPDATE avgproductperservice
        SET numoptservice = numoptservice + temporaryChosAdd + temporaryChosPack,
            numpackage = numpackage +1
        WHERE package_id =NEW.refPack;
    END IF;
END;

CREATE TRIGGER upAvg
    BEFORE UPDATE ON avgproductperservice for each row
BEGIN
    SET NEW.avgnumber=NEW.numoptservice/NEW.numpackage;
end;
