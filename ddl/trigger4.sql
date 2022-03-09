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
    DECLARE temporaryChos, temporaryOptOwn  int;
    IF NEW.valid = 1 THEN
        SELECT c.refOptService, count(*)
        FROM 'order' as o
        UPDATE avgproductperservice
        SET numoptservice = numoptservice + (temporaryCho+temporaryOptOwn),
            justPack = justPack + ( SELECT f.fee
                                    FROM feeperiod f
                                    WHERE f.refPackage=NEW.refPack and f.periodo=NEW.periodo)
        WHERE package_id =NEW.refPack;
    END IF;
END;



CREATE DEFINER  = CURRENT_USER trigger addOptProduct
    after insert
    on `order`
    for each row
begin

    IF NEW.isValid = true THEN
        DELETE FROM totalnumberofoptionalproduct t
        WHERE t.package_id IN (SELECT s.packageSelected
                               FROM servicepackage s
                               WHERE s.servicePackage_id = NEW.servicePackageAssociated );


        INSERT INTO totalnumberofoptionalproduct
        SELECT ss.servicePackageToSelect_id, count(*)
        FROM `order` as o
                 JOIN dbtelco.servicepackage as s on o.servicePackageAssociated = s.servicePackage_id
                 JOIN dbtelco.servicepackagetoselect as ss on s.packageSelected = ss.servicePackageToSelect_id
                 JOIN dbtelco.addedproduct as a on a.servicePackage_id = o.servicePackageAssociated
        WHERE o.isValid = true AND ss.servicePackageToSelect_id IN (SELECT s.packageSelected
                                                                    FROM servicepackage s
                                                                    WHERE s.servicePackage_id = NEW.servicePackageAssociated)

        GROUP BY ss.servicePackageToSelect_id;


        DELETE FROM avgnumofoptproductssoldperpackage
        WHERE package_id IN (   SELECT s.packageSelected
                                FROM servicepackage s
                                WHERE s.servicePackage_id = NEW.servicePackageAssociated);

        INSERT INTO avgnumofoptproductssoldperpackage
        SELECT t.package_id, IFNULL((o.total / t.totalPurchases), 0.0)
        FROM dbtelco.totalpurchasesperpackage as t
                 LEFT OUTER JOIN dbtelco.totalnumberofoptionalproduct as o on t.package_id = o.package_id

        WHERE t.package_id IN (SELECT s.packageSelected
                               FROM servicepackage s
                               WHERE s.servicePackage_id = NEW.servicePackageAssociated);


    END IF;
end





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